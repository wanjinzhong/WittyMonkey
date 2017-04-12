package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Role;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IRoleService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleRole;
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

    @Autowired
    private IRoleService roleService;

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
}
