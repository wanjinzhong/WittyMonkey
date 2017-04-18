package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IFinanceService;
import com.wittymonkey.service.IFinanceTypeService;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constriant;
import com.wittymonkey.vo.SimpleFinance;
import com.wittymonkey.vo.SimpleFloor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    @Autowired
    private IUserService userService;

    @Autowired
    private IHotelService hotelService;

    @RequestMapping(value = "getFinanceByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getFinanceByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = financeService.getTotal(hotel.getId(), type);
        List<Finance> finances = financeService.getFinanceByPage(hotel.getId(), type, (curr - 1) * pageSize, pageSize);
        List<SimpleFinance> simpleFinances = ChangeToSimple.financeList(finances);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleFinances);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddFinance", method = RequestMethod.GET)
    public String toAddFinance(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<FinanceType> financeTypes = financeTypeService.getFinanceTypeByPage(hotel.getId(), Constriant.FINANCE_TYPE_DEFAULT_IN, null, null);
        request.setAttribute("types", financeTypes);
        return "finance_add";
    }

    /**
     * 保存
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>类型不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>金额不正确</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>金额不能为负</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>备注过长</td>
     * </tr>
     */
    @RequestMapping(value = "saveFinanceType", method = RequestMethod.POST)
    @ResponseBody
    public String saveFinanceType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer type = null;
        Double money = null;
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        try {
            type = Integer.parseInt(request.getParameter("type"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            json.put("status", 400);
            return json.toJSONString();
        }
        FinanceType financeType = financeTypeService.getFinanceTypeById(type);
        if (financeType == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            money = Double.parseDouble(request.getParameter("money"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            json.put("status", 410);
            return json.toJSONString();
        }
        if (money < 0) {
            json.put("status", 411);
            return json.toJSONString();
        }
        String note = request.getParameter("note");
        if (StringUtils.isNotBlank(note) && note.length() >= 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        Finance finance = new Finance();
        finance.setEntryDatetime(new Date());
        finance.setEntryUser(userService.getUserById(loginUser.getId()));
        finance.setFinanceType(financeType);
        finance.setMoney(money);
        finance.setNote(note);
        financeService.save(finance);
        json.put("status", 200);
        return json.toJSONString();
    }
}
