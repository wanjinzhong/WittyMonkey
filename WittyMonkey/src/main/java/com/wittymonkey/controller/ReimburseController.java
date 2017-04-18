package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Reimburse;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IReimburseService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleReimbuse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/18.
 */
@Controller
public class ReimburseController {

    @Autowired
    private IReimburseService reimburseService;

    @RequestMapping(value = "getReimburseByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getReimburseByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer status = Integer.parseInt(request.getParameter("type"));
        String fromStr = request.getParameter("from");
        String toStr = request.getParameter("to");
        Date from = null;
        Date to = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            from = dateFormat.parse(fromStr);
        } catch (ParseException e) {
        }
        try{
            to = dateFormat.parse(toStr);
        } catch (ParseException e){
        }
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = reimburseService.getTotal(hotel.getId(),status, from, to);
        List<Reimburse> reimburses = reimburseService.getReimburseByPage(hotel.getId(),status,from, to,  (curr - 1) * pageSize, pageSize);
        List<SimpleReimbuse> simpleReimbuses = ChangeToSimple.reimbuseList(reimburses);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleReimbuses);
        json.put("data", array);
        return json.toJSONString();
    }
}
