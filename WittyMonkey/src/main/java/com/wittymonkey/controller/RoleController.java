package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constriant;
import com.wittymonkey.vo.SimpleMenu;
import com.wittymonkey.vo.SimpleRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by neilw on 2017/4/12.
 */
@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toRoleManage", method = RequestMethod.GET)
    public String toRoleManage() {
        return "role_manage";
    }

    @RequestMapping(value = "getRoleByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getRoleByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = roleService.getTotal(hotel.getId());
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), (curr - 1) * pageSize, pageSize);
        for (Role role : roles) {
            Iterator<Menu> iterator = role.getMenus().iterator();
            while (iterator.hasNext()) {
                if (!iterator.next().getConfigurable()) {
                    iterator.remove();
                }
            }
        }
        List<SimpleRole> simpleRoles = ChangeToSimple.roleList(loginUser.getSetting().getLang(), roles);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRoles);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddRole", method = RequestMethod.GET)
    public String toAddRole(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        List<Menu> menus = menuService.getAllConfigurable();
        List<SimpleMenu> simpleMenus = ChangeToSimple.menuList(loginUser.getSetting().getLang(), menus);
        request.setAttribute("menus", simpleMenus);
        return "role_add";
    }

    @RequestMapping(value = "toEditRole", method = RequestMethod.GET)
    public String toEditRole(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Role role = roleService.getRoleById(id);
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        List<Menu> menus = menuService.getAllConfigurable();
        List<SimpleMenu> simpleMenus = ChangeToSimple.menuList(loginUser.getSetting().getLang(), menus);
        for (SimpleMenu simpleMenu : simpleMenus) {
            simpleMenu.setSelected(false);
            for (Menu menu : role.getMenus()) {
                if (menu.getId().equals(simpleMenu.getId())) {
                    simpleMenu.setSelected(true);
                    break;
                }
            }

        }
        request.setAttribute("menus", simpleMenus);
        request.getSession().setAttribute("editRole", role);
        return "role_edit";
    }

    @RequestMapping(value = "validateRoleName", method = RequestMethod.GET)
    @ResponseBody
    public String validateRoleName(HttpServletRequest request) {
        String type = request.getParameter("type");
        String roleName = request.getParameter("roleName");
        JSONObject json = new JSONObject();
        json.put("status", validateRoleName(request, type, roleName));
        return json.toJSONString();
    }

    /**
     * 验证角色名
     *
     * @param type
     * @param roleName
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>角色名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>角色名过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>角色名存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>角色名不存在</td>
     * </tr>
     */
    public Integer validateRoleName(HttpServletRequest request, String type, String roleName) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String editRole = null;
        if (StringUtils.isBlank(roleName)) {
            return 400;
        }
        if (roleName.trim().length() > 10) {
            return 401;
        }
        if (Constriant.UPDATE.equals(type)) {
            editRole = ((Role) request.getSession().getAttribute("editRole")).getName();
        }
        Role role = roleService.getRoleByRoleName(hotel.getId(), roleName);

        if (role != null) {
            if (Constriant.ADD.equals(type) || Constriant.DELETE.equals(type)
                    || (Constriant.UPDATE.equals(type) && !editRole.equals(roleName))) {
                return 200;
            } else {
                return 201;
            }
        } else {
            return 201;
        }
    }

    /**
     * 保存角色
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>角色名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>角色名过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>角色名存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>没有选择菜单</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>已有相同菜单的角色</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveRole", method = RequestMethod.GET)
    @ResponseBody
    public String saveRole(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        JSONObject json = new JSONObject();
        String roleName = request.getParameter("name");
        String note = request.getParameter("note");
        String[] menusIdStr = request.getParameterValues("menu");
        List<Integer> menusId = changeToInteger(menusIdStr);
        Integer roleVali = validateRole(request,Constriant.ADD,roleName,note,menusId);
        if (!new Integer(200).equals(roleVali)){
            json.put("status", roleVali);
            return json.toJSONString();
        }
        Role newRole = new Role();
        newRole.setHotel(hotelService.findHotelById(hotel.getId()));
        newRole.setEntryDatetime(new Date());
        newRole.setEntryUser(userService.getUserById(loginUser.getId()));
        newRole.setName(roleName);
        newRole.setNote(note);
        // 添加用户选择的可配置的菜单
        for (Integer menuId : menusId) {
            Menu menu = menuService.getMenuById(menuId);
            if (menu != null && menu.getConfigurable()) {
                newRole.getMenus().add(menu);
            }
        }
        // 添加不可配置的菜单
        newRole.getMenus().addAll(menuService.getAllUnconfigurable());
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
        for (Role role : roles) {
            if (newRole.isMenuSame(role)) {
                json.put("status", 421);
                json.put("duplicateName", role.getName());
                return json.toJSONString();
            }
        }
        roleService.saveRole(newRole);
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 删除角色
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有这个角色</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>删除成功</td>
     * </tr>
     */
    @RequestMapping(value = "deleteRole", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRole(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        Integer id = null;
        JSONObject jsonObject = new JSONObject();
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            jsonObject.put("status", 400);
            return jsonObject.toJSONString();
        }
        try {
            Role role = roleService.getRoleById(id);
            roleService.deleteRole(role);
        } catch (SQLException e) {
            jsonObject.put("status", 400);
            return jsonObject.toJSONString();
        }
        jsonObject.put("status", 200);
        return jsonObject.toJSONString();
    }

    /**
     * 更新角色
     * @param request
     * @return
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>角色名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>角色名过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>角色名存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>没有选择菜单</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>已有相同菜单的角色</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>修改成功</td>
     * </tr>
     */
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Role editRole = (Role) request.getSession().getAttribute("editRole");
        JSONObject json = new JSONObject();
        String roleName = request.getParameter("name");
        String note = request.getParameter("note");
        String[] menusIdStr = request.getParameterValues("menu");
        List<Integer> menusId = changeToInteger(menusIdStr);

        Integer roleVali = validateRole(request,Constriant.UPDATE,roleName,note,menusId);
        if (!new Integer(200).equals(roleVali)){
            json.put("status", roleVali);
            return json.toJSONString();
        }
        Role role = roleService.getRoleById(editRole.getId());
        role.getMenus().clear();
        // 添加用户选择的可配置的菜单
        for (Integer menuId : menusId) {
            Menu menu = menuService.getMenuById(menuId);
            if (menu != null && menu.getConfigurable()) {
                role.getMenus().add(menu);
            }
        }
        // 添加不可配置的菜单
        role.getMenus().addAll(menuService.getAllUnconfigurable());
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
        for (Role r : roles) {
            if (role.isMenuSame(r) && !role.getId().equals(r.getId())) {
                json.put("status", 421);
                json.put("duplicateName", role.getName());
                return json.toJSONString();
            }
        }
        role.setNote(note);
        role.setEntryUser(userService.getUserById(loginUser.getId()));
        role.setEntryDatetime(new Date());
        role.setName(roleName);
        roleService.saveRole(role);
        json.put("status", 200);
        return json.toJSONString();
    }

    private List<Integer> changeToInteger(String[] menusIdStr){
        List<Integer> menusId = new ArrayList<Integer>();
        if (menusIdStr == null){
            return new ArrayList<Integer>();
        }
        for (String id : menusIdStr) {
            Integer menuId = null;
            try {
                menuId = Integer.parseInt(id);
                menusId.add(menuId);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return menusId;
    }

    /**
     * 验证角色表单
     * @param request
     * @param type
     * @param roleName
     * @param note
     * @param menusId
     * @return
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>角色名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>角色名过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>角色名存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>没有选择菜单</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>验证通过</td>
     * </tr>
     */
    public Integer validateRole(HttpServletRequest request, String type, String roleName, String note, List<Integer> menusId){
        Integer roleNameVali = validateRoleName(request, type, roleName);
        if (!new Integer(201).equals(roleNameVali)) {
            if (new Integer(200).equals(roleNameVali)) {
                return 402;
            } else {
                return roleNameVali;
            }
        }
        if (StringUtils.isNotBlank(note) && note.length() > 1024) {
            return 410;
        }
        if (menusId.size() <= 0) {
            return  420;
        }
        return 200;
    }
}
