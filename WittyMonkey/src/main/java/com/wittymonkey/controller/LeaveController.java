package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.LeaveVO;
import com.wittymonkey.vo.SimpleLeaveHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by neilw on 2017/4/26.
 */
@Controller
public class LeaveController {

    @Autowired
    private ILeaveHeaderService leaveHeaderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILeaveTypeService leaveTypeService;

    @Autowired
    private ISalaryRecordService salaryRecordService;

    @Autowired
    private ISalaryService salaryService;

    @RequestMapping(value = "getLeaveByPage", method = GET)
    @ResponseBody
    public String getLeaveByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer type = Integer.parseInt(request.getParameter("type"));
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = leaveHeaderService.getTotalByStatus(hotel.getId(), type);
        List<LeaveHeader> leaves = leaveHeaderService.getLeaveHeaderByStatus(hotel.getId(), type, (curr - 1) * pageSize, pageSize);
        List<LeaveVO> leaveVOS = ChangeToSimple.assymblyLeaveVOs(leaves);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(leaveVOS);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddLeave", method = GET)
    public String toAddLeave(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<User> users = userService.getUserByPage(hotel.getId(), Constraint.USER_INCUMBENT, null, null);
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes(hotel.getId(), null, null);
        request.setAttribute("users", users);
        request.setAttribute("leaveTypes", leaveTypes);
        return "leave_add";
    }

    @RequestMapping(value = "calcDeduct", method = GET)
    public String calcDeduct(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String fromDate = request.getParameter("from").trim();
        String toDate = request.getParameter("to").trim();
        String days = request.getParameter("days").trim();
        String applyUser = request.getParameter("applyUser").trim();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date from = null;
        Date to = null;
        try {
            from = dateFormat.parse(fromDate);
            to = dateFormat.parse(toDate);
        } catch (ParseException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Double time = null;
        try {
            time = Double.parseDouble(days);
        } catch (NumberFormatException e) {
            json.put("status", 410);
            return json.toJSONString();
        }
        Integer dayDiff = DateUtil.dateDiffDays(from, to) + 1;
        if (time < dayDiff || time > dayDiff + 1){
            json.put("status", 411);
            return json.toJSONString();
        }
        Salary salary = salaryService.getSalaryByStaffId(Integer.parseInt(applyUser));
        // 获取开始时间点的工资
        SalaryRecord fromRecord = salaryRecordService.getSalaryRecordAtDate(salary.getId(),from);
        // 获取请假时间段内的工资变动
        List<SalaryRecord> salaryRecords = salaryRecordService.getSalaryRecordByDateRange(salary.getId(),from, to);
        return json.toJSONString();
    }

}
