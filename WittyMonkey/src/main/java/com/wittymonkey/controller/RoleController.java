package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IMenuService;
import com.wittymonkey.service.IRoleService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleMenu;
import com.wittymonkey.vo.SimpleRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by neilw on 2017/4/12.
 */
@Controller
public class RoleController {

    public static final String ADD = "add";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "toRoleManage", method = RequestMethod.GET)
    public String toRoleManage(){
        return "role_manage";
    }

    @RequestMapping(value = "getRoleByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getRoleByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = roleService.getTotal(hotel.getId());
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleRole> simpleRoles = ChangeToSimple.roleList(loginUser.getSetting().getLang(),roles);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRoles);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddRole", method = RequestMethod.GET)
    public String toAddRole(HttpServletRequest request){
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        List<Menu> menus = menuService.getAll();
        List<SimpleMenu> simpleMenus = ChangeToSimple.menuList(loginUser.getSetting().getLang(), menus);
        request.setAttribute("menus", simpleMenus);
        return "role_add";
    }

    @RequestMapping(value = "validateRoleName", method = RequestMethod.GET)
    @ResponseBody
    public String validateRoleName(HttpServletRequest request){
        String type = request.getParameter("type");
        String roleName = request.getParameter("roleName");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        JSONObject json = new JSONObject();
        json.put("status", validateRoleName(request, type,roleName,hotel));
        return json.toJSONString();
    }

    /**
     * 验证角色名
     * @param type
     * @param roleName
     * @param hotel
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
     * <td>200</td>
     * <td>角色名存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>角色名不存在</td>
     * </tr>
     */
    public Integer validateRoleName(HttpServletRequest request,String type, String roleName, Hotel hotel){
        String editRole = null;
        if (StringUtils.isBlank(roleName)){
            return 400;
        }
        if (roleName.trim().length() > 50){
            return 401;
        }
        if (UPDATE.equals(type)){
            editRole = ((Role) request.getSession().getAttribute("editRole")).getName();
        }
        Role role = roleService.getRoleByRoleName(hotel.getId(), roleName);

        if (role != null){
            if (ADD.equals(type) || DELETE.equals(type)
                    || (UPDATE.equals(type) && editRole.equals(roleName))){
                return 200;
            } else {
                return 201;
            }
        } else {
            return 201;
        }
    }
}
