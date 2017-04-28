package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.LeaveVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    /**
     * 计算扣薪
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>时间不正确</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>计算完成</td>
     * </tr>
     */
    @RequestMapping(value = "calcDeduct", method = GET)
    @ResponseBody
    public String calcDeduct(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String fromDate = request.getParameter("from").trim();
        String toDate = request.getParameter("to").trim();
        String applyUser = request.getParameter("applyUser").trim();
        Integer typeId = Integer.parseInt(request.getParameter("type").trim());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isBlank(fromDate) || StringUtils.isBlank(toDate)) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Date from = null;
        Date to = null;
        try {
            from = dateFormat.parse(fromDate);
            to = dateFormat.parse(toDate);
        } catch (ParseException e) {
            json.put("status", 401);
            return json.toJSONString();
        }
        User user = userService.getUserById(Integer.parseInt(applyUser));
        LeaveType type = leaveTypeService.getLeaveTypeById(typeId);
        Double deduct = getDeduct(from, to, user, type);
        json.put("status", 200);
        json.put("deduct", deduct);
        return json.toJSONString();
    }

    private Double getDeduct(Date from, Date to, User user, LeaveType type) {
        Double deduct = 0.0;
        Salary salary = salaryService.getSalaryByStaffId(user.getId());
        // 这个月开始请假时间
        Date thisMonthFrom = from;
        while (!to.before(thisMonthFrom)) {
            // 下月1号
            Date nextFirstDayOfMonth = DateUtil.nextMonthFirstDate(thisMonthFrom);
            // 当月工资
            SalaryRecord salaryRecord = salaryRecordService.getSalaryRecordAtDate(salary.getId(), thisMonthFrom);
            // 当月扣薪
            Double thisMonthDeduct = 0.0;
            // 如果这个月没有找到工资记录，则跳过扣薪
            if (salaryRecord == null) {
                thisMonthFrom = nextFirstDayOfMonth;
                continue;
            }
            // 如果结束时间小于下个月1号，说明请假这个月结束，计算结束
            if (!nextFirstDayOfMonth.before(to)) {
                Integer day = DateUtil.dateDiffDays(thisMonthFrom, to) + 1;
                thisMonthDeduct = day * (salaryRecord.getMoney() / user.getWorkDays() * type.getDeduct());

            }
            // 如果结束时间大于等于下个月1号，说明这个月到月底均为请假
            else {
                Integer day = DateUtil.dateDiffDays(thisMonthFrom, nextFirstDayOfMonth);
                thisMonthDeduct = day * (salaryRecord.getMoney() / user.getWorkDays() * type.getDeduct());
            }
            // 防止扣薪到负数
            if (thisMonthDeduct >= salaryRecord.getMoney()) {
                thisMonthDeduct = salaryRecord.getMoney();
            }
            deduct += thisMonthDeduct;
            thisMonthFrom = nextFirstDayOfMonth;
        }
        // 四舍五入保留两位小数
        BigDecimal b = new BigDecimal(deduct);
        deduct = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return deduct;
    }

    /**
     * 保存请假
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>时间为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>时间不正确</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>申请说明过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>审核说明过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveLeave", method = POST)
    @ResponseBody
    public String saveLeave(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer applyUser = Integer.parseInt(request.getParameter("applyUser").trim());
        Integer typeId = Integer.parseInt(request.getParameter("type").trim());
        String fromDate = request.getParameter("from").trim();
        String toDate = request.getParameter("to").trim();
        Integer status = Integer.parseInt(request.getParameter("status").trim());
        String applyNote = request.getParameter("applyNote");
        String optNote = request.getParameter("optNote");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isBlank(fromDate) || StringUtils.isBlank(toDate)) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Date from = null;
        Date to = null;
        try {
            from = dateFormat.parse(fromDate);
            to = dateFormat.parse(toDate);
        } catch (ParseException e) {
            json.put("status", 401);
            return json.toJSONString();
        }
        if (StringUtils.isNotBlank(applyNote) && applyNote.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        if (StringUtils.isNotBlank(optNote) && optNote.length() > 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        LeaveHeader header = new LeaveHeader();
        Date now = new Date();
        header.setApplyDatetime(now);
        header.setApplyUser(userService.getUserById(applyUser));
        header.setApplyUserNote(applyNote);
        header.setStatus(status);
        if (!Constraint.LEAVE_STATUS_PENDING.equals(status)) {
            header.setEntryDatetime(now);
            header.setEntryUser(userService.getUserById(user.getId()));
            header.setEntryUserNote(optNote);
        }
        LeaveType type = leaveTypeService.getLeaveTypeById(typeId);
        header.setLeaveType(type);
        List<LeaveDetail> leaveDetails = new ArrayList<LeaveDetail>();

        Salary salary = salaryService.getSalaryByStaffId(user.getId());
        // 这个月开始请假时间
        Date thisMonthFrom = from;
        // 这个月请假结束时间
        Date thisMonthTo = null;
        while (!to.before(thisMonthFrom)) {
            // 下月1号
            Date nextFirstDayOfMonth = DateUtil.nextMonthFirstDate(thisMonthFrom);
            // 当月工资
            SalaryRecord salaryRecord = salaryRecordService.getSalaryRecordAtDate(salary.getId(), thisMonthFrom);
            // 当月扣薪
            Double thisMonthDeduct = 0.0;
            // 如果这个月没有找到工资记录，则跳过扣薪
            if (salaryRecord == null) {
                thisMonthFrom = nextFirstDayOfMonth;
                continue;
            }
            // 当月请假天数
            Integer day;
            // 如果结束时间小于下个月1号，说明请假这个月结束，计算结束
            if (!nextFirstDayOfMonth.before(to)) {
                day = DateUtil.dateDiffDays(thisMonthFrom, to) + 1;
                thisMonthDeduct = day * (salaryRecord.getMoney() / user.getWorkDays() * type.getDeduct());
                thisMonthTo = to;
            }
            // 如果结束时间大于等于下个月1号，说明这个月到月底均为请假
            else {
                day = DateUtil.dateDiffDays(thisMonthFrom, nextFirstDayOfMonth);
                thisMonthDeduct = day * (salaryRecord.getMoney() / user.getWorkDays() * type.getDeduct());
                thisMonthTo = DateUtil.lastDayOfMonth(thisMonthFrom);
            }
            // 防止扣薪到负数
            if (thisMonthDeduct >= salaryRecord.getMoney()) {
                thisMonthDeduct = salaryRecord.getMoney();
            }
            LeaveDetail detail = new LeaveDetail();
            detail.setDays(day);
            detail.setDeduct(thisMonthDeduct);
            detail.setFrom(thisMonthFrom);
            detail.setTo(thisMonthTo);
            detail.setLeaveHeader(header);
            leaveDetails.add(detail);
            thisMonthFrom = nextFirstDayOfMonth;
        }
        header.setLeaveDetails(leaveDetails);
        leaveHeaderService.save(header);
        json.put("status", 200);
        return json.toJSONString();
    }
}
