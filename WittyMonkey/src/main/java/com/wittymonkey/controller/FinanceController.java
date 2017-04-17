package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IFinanceService;
import com.wittymonkey.service.IFinanceTypeService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constriant;
import com.wittymonkey.vo.SimpleFinance;
import com.wittymonkey.vo.SimpleFloor;
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
 * Created by neilw on 2017/4/17.
 */
@Controller
public class FinanceController {

    @Autowired
    private IFinanceService financeService;

    @Autowired
    private IFinanceTypeService financeTypeService;

    @RequestMapping(value = "getFinanceByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getFinanceByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = financeService.getTotal(hotel.getId(), type);
        List<Finance> finances = financeService.getFinanceByPage(hotel.getId(),type, (curr - 1) * pageSize, pageSize);
        List<SimpleFinance> simpleFinances = ChangeToSimple.financeList(finances);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleFinances);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddFinance", method = RequestMethod.GET)
    public String toAddFinance(HttpServletRequest request){
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<FinanceType> financeTypes = financeTypeService.getFinanceTypeByPage(hotel.getId(), Constriant.FINANCE_TYPE_DEFAULT_IN, null, null);
        request.setAttribute("types", financeTypes);
        return "finance_add";
    }
}
