package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Leave;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.ILeaveService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.SimpleLeave;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by neilw on 2017/4/26.
 */
@Controller
public class LeaveController {

    @Autowired
    private ILeaveService leaveService;

    @RequestMapping(value = "getLeaveByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaveByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer type = Integer.parseInt(request.getParameter("type"));
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = leaveService.getTotalByStatus(hotel.getId(),type);
        List<Leave> leaves = leaveService.getLeaveByStatus(hotel.getId(), type, (curr - 1) * pageSize, pageSize);
        List<SimpleLeave> simpleLeaves = ChangeToSimple.leaveList(leaves);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleLeaves);
        json.put("data", array);
        return json.toJSONString();
    }
}
