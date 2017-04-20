package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Salary;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.ISalaryService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SalaryVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neilw on 2017/4/20.
 */
@Controller
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @RequestMapping(value = "getSalaryByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getSalaryByPage(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(Constraint.SALARY_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        Integer type = Integer.parseInt(request.getParameter("type"));
        switch (type){
            case 2:
                String name = request.getParameter("name");
                if (StringUtils.isNotBlank(name)) {
                    param.put(Constraint.SALARY_SEARCH_CONDITION_STAFF_NAME, name);
                }
                break;
            case 3:
                String staffNo = request.getParameter("staffNo");
                if (StringUtils.isNotBlank(staffNo)) {
                    param.put(Constraint.SALARY_SEARCH_CONDITION_STAFF_NO, staffNo);
                }
        }
        Integer count = salaryService.getTotal(param);
        List<Salary> salaries = salaryService.getSalaryByPage(param,(curr - 1) * pageSize, pageSize);
        List<SalaryVO> salaryVOS = ChangeToSimple.convertSalariesByTime(salaries, new Date());
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(salaryVOS);
        json.put("data", array);
        return json.toJSONString();
    }
}
