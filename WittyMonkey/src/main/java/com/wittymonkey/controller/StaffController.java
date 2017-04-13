package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.dao.IHotelDao;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.IRoleService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.util.IDCardValidate;
import com.wittymonkey.util.MD5Util;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleMenu;
import com.wittymonkey.vo.SimpleRole;
import com.wittymonkey.vo.SimpleUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by neilw on 2017/4/7.
 */
@Controller
public class StaffController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "getStaffByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getStaffByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = userService.getTotalByHotel(hotel.getId());
        List<User> users = userService.getUserByPage(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleUser> simpleUsers = ChangeToSimple.userList(users);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleUsers);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddStaff", method = RequestMethod.GET)
    public String toAddStaff(HttpServletRequest request){
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
        request.setAttribute("roles", roles);
        return "staff_add";
    }

    /**
     * 添加员工
     * @param request
     * @return<table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>添加成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有填写真实姓名</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>真实姓名过长</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>身份证号不正确</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>电话号码过长</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>邮箱过长</td>
     * </tr>
     */
    @RequestMapping(value = "saveStaff", method = RequestMethod.GET)
    @ResponseBody
    public String aveStaff(HttpServletRequest request){
        JSONObject json = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String realName = request.getParameter("realName");
        String idcard = request.getParameter("idcard");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String[] rolesStr = request.getParameterValues("roles");
        Integer staffVali = validateStaff(realName,idcard,tel,email);
        if (staffVali != 200){
            json.put("status", staffVali);
            return json.toJSONString();
        }
        List<Role> roles = new ArrayList<Role>();
        if (rolesStr != null){
            for(String str : rolesStr){
                try{
                    Integer id = Integer.parseInt(str);
                    Role role = roleService.getRoleById(id);
                    if (role != null){
                        roles.add(role);
                    }
                } catch (NumberFormatException e){
                    continue;
                }
            }
        }

        User user = new User();
        String initPassword = "000000";
        String secritePwd = MD5Util.encrypt(initPassword);
        user.setRealName(realName);
        user.setHotel(hotelService.findHotelById(hotel.getId()));
        user.setEntryUser(userService.getUserById(loginUser.getId()));
        user.setPassword(secritePwd);
        user.setIdCardNo(idcard);
        user.setEntryDatetime(new Date());
        user.setTel(tel);
        user.setEmail(email);
        user.setRoles(roles);
        String staffNo = userService.getNextStaffNoByHotel(hotel.getId());
        user.setStaffNo(staffNo);
        Setting setting = new Setting();
        setting.setLang(Setting.LANG_ZH_CN);
        setting.setPageSize(10);
        user.setSetting(setting);
        user.setRegistDate(new Date());
        Object obj = userService.saveUser(user);
        json.put("status", 200);
        json.put("staffNo", staffNo);
        json.put("initPwd", initPassword);
        return json.toJSONString();
    }


    public Integer validateStaff(String realName, String idcard, String tel, String email){
        if (StringUtils.isBlank(realName)){
            return 400;
        }
        if (realName.length() > 20){
            return 401;
        }
        if (!IDCardValidate.validate(idcard)){
            return 410;
        }
        if (StringUtils.isNotBlank(tel) && tel.trim().length()> 15){
            return 420;
        }
        if (StringUtils.isNotBlank(email) && email.trim().length() > 50){
            return 430;
        }
        return 200;
    }

    @RequestMapping(value = "toEditStaff", method = RequestMethod.GET)
    public String toEditStaff(HttpServletRequest request){
        String idStr = request.getParameter("id");
        try{
            Integer id = Integer.parseInt(idStr);
            User staff = userService.getUserById(id);
            request.getSession().setAttribute("editStaff", staff);
            Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
            List<SimpleRole> simpleRoles = ChangeToSimple.roleList(loginUser.getSetting().getLang(),roles);
            for (SimpleRole role : simpleRoles){
                role.setSelected(false);
                for(Role myRole : staff.getRoles()){
                    if (myRole.getId().equals(role.getId())){
                        role.setSelected(true);
                    }
                }
            }
            request.setAttribute("roles", simpleRoles);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return "staff_edit";
    }
}
