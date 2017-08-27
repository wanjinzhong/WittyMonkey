package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.*;
import com.wittymonkey.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private ILeaveDetailService leaveDetailService;

    @Autowired
    private ISalaryHistoryService salaryHistoryService;

    @RequestMapping(value = "getStaffByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getStaffByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = userService.getTotalByHotel(hotel.getId(), type);
        List<User> users = userService.getUserByPage(hotel.getId(), type, (curr - 1) * pageSize, pageSize);
        List<SimpleUser> simpleUsers = ChangeToSimple.userList(users);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleUsers);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddStaff", method = RequestMethod.GET)
    public String toAddStaff(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
        request.setAttribute("roles", roles);
        return "staff_add";
    }

    /**
     * 添加员工
     *
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
    @RequestMapping(value = "saveStaff", method = RequestMethod.POST)
    @ResponseBody
    public String saveStaff(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String realName = request.getParameter("realName").trim();
        String idcard = request.getParameter("idcard").trim();
        String tel = request.getParameter("tel").trim();
        String email = request.getParameter("email").trim();
        String workDays = request.getParameter("workDays").trim();
        String[] rolesStr = request.getParameterValues("roles");
        Integer staffVali = validateStaff(realName, idcard, tel, email, workDays);
        if (staffVali != 200) {
            json.put("status", staffVali);
            return json.toJSONString();
        }
        List<Role> roles = new ArrayList<Role>();
        if (rolesStr != null) {
            for (String str : rolesStr) {
                try {
                    Integer id = Integer.parseInt(str);
                    Role role = roleService.getRoleById(id);
                    if (role != null) {
                        roles.add(role);
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        Double work = Double.parseDouble(workDays);
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
        user.setWorkDays(work);
        String staffNo = userService.getNextStaffNoByHotel(hotel.getId());
        user.setStaffNo(staffNo);
        Setting setting = new Setting();
        setting.setLang(Constraint.LANG_ZH_CN);
        setting.setPageSize(10);
        setting.setUser(user);
        user.setSetting(setting);
        user.setRegistDate(new Date());
        Salary salary = new Salary();
        salary.setStaff(user);
        user.setSalary(salary);
        userService.saveUser(user);
        json.put("status", 200);
        json.put("staffNo", staffNo);
        json.put("initPwd", initPassword);
        return json.toJSONString();
    }


    public Integer validateStaff(String realName, String idcard, String tel, String email, String workDays) {
        if (StringUtils.isBlank(realName)) {
            return 400;
        }
        if (realName.length() > 20) {
            return 401;
        }
        if (!IDCardValidate.validate(idcard)) {
            return 410;
        }
        if (StringUtils.isNotBlank(tel) && tel.trim().length() > 15) {
            return 420;
        }
        if (StringUtils.isNotBlank(email) && email.trim().length() > 50) {
            return 430;
        }
        if (StringUtils.isBlank(workDays)) {
            return 440;
        }
        try {
            Double work = Double.parseDouble(workDays);
            if (work <= 0 || work > 31) {
                return 441;
            }
        } catch (NumberFormatException e) {
            return 441;
        }
        return 200;
    }

    @RequestMapping(value = "toEditStaff", method = RequestMethod.GET)
    public String toEditStaff(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        try {
            Integer id = Integer.parseInt(idStr);
            User staff = userService.getUserById(id);
            request.getSession().setAttribute("editStaff", staff);
            Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            List<Role> roles = roleService.getRoleByPage(hotel.getId(), null, null);
            List<SimpleRole> simpleRoles = ChangeToSimple.roleList(loginUser.getSetting().getLang(), roles);
            for (SimpleRole role : simpleRoles) {
                role.setSelected(false);
                for (Role myRole : staff.getRoles()) {
                    if (myRole.getId().equals(role.getId())) {
                        role.setSelected(true);
                    }
                }
            }
            request.setAttribute("roles", simpleRoles);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "staff_edit";
    }

    @RequestMapping(value = "updateStaff", method = RequestMethod.POST)
    @ResponseBody
    public String updateStaff(HttpServletRequest request) {
        User editStaff = (User) request.getSession().getAttribute("editStaff");
        JSONObject json = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String realName = request.getParameter("realName").trim();
        String idcard = request.getParameter("idcard").trim();
        String tel = request.getParameter("tel").trim();
        String email = request.getParameter("email").trim();
        String workDays = request.getParameter("workDays").trim();
        String[] rolesStr = request.getParameterValues("roles");
        Integer staffVali = validateStaff(realName, idcard, tel, email, workDays);
        if (staffVali != 200) {
            json.put("status", staffVali);
            return json.toJSONString();
        }
        List<Role> roles = new ArrayList<Role>();
        if (rolesStr != null) {
            for (String str : rolesStr) {
                try {
                    Integer id = Integer.parseInt(str);
                    Role role = roleService.getRoleById(id);
                    if (role != null) {
                        roles.add(role);
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        Double work = Double.parseDouble(workDays);
        User staff = userService.getUserById(editStaff.getId());
        staff.setRealName(realName);
        staff.setIdCardNo(idcard);
        staff.setTel(tel);
        staff.setEmail(email);
        staff.setWorkDays(work);
        staff.setRoles(roles);
        staff.setEntryUser(userService.getUserById(loginUser.getId()));
        staff.setEntryDatetime(new Date());
        userService.saveUser(staff);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "toDimission", method = RequestMethod.GET)
    public String toDimission(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        request.getSession().setAttribute("dimissionUser", user);
        Date thisMonth = DateUtil.thisMonthFirstDate(new Date());
        Integer dateDiff = DateUtil.dateDiffDays(thisMonth, new Date()) + 1;
        Salary salary = salaryService.getSalaryByStaffId(user.getId());
        SalaryVO salaryVO = ChangeToSimple.convertSalaryByTime(salary, thisMonth);
        SalaryPayroll salaryPayroll = new SalaryPayroll();
        salaryPayroll.setStaffId(user.getId());
        salaryPayroll.setStaffNo(user.getStaffNo());
        salaryPayroll.setStaffName(user.getRealName());
        if (salaryVO != null && salaryVO.getMoney() != null) {
            salaryPayroll.setBasic(salaryVO.getMoney() * dateDiff / user.getWorkDays());
            salaryPayroll.setSalaryDate(thisMonth);
            List<LeaveDetail> leaveDetails = leaveDetailService.getLeaveDateilByUserInRange(user.getId(), thisMonth, new Date());
            Double leavePay = 0.0;
            for (LeaveDetail detail : leaveDetails) {
                leavePay += detail.getDeduct();
            }
            salaryPayroll.setLeave(NumberUtil.round(leavePay, 2));
        }
        request.getSession().setAttribute("dimissionPayroll", salaryPayroll);
        return "staff_dimission";
    }

    @RequestMapping(value = "dimission", method = RequestMethod.POST)
    @ResponseBody
    public String dimission(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        User user = (User) request.getSession().getAttribute("dimissionUser");
        SalaryPayroll payroll = (SalaryPayroll) request.getSession().getAttribute("dimissionPayroll");
        Double otherPay = null;
        Double bonusPay = null;
        try {
            String other = request.getParameter("other");
            if (StringUtils.isBlank(other)) {
                otherPay = 0.0;
            } else {
                otherPay = Double.parseDouble(other);
            }
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            String bonus = request.getParameter("benefits");
            if (StringUtils.isBlank(bonus)) {
                bonusPay = 0.0;
            } else {
                bonusPay = Double.parseDouble(bonus);
            }
        } catch (NumberFormatException e) {
            json.put("status", 401);
            return json.toJSONString();
        }
        String note = request.getParameter("note");
        if (StringUtils.isNotBlank(note) && note.length() > 1024){
            json.put("status", 410);
            return json.toJSONString();
        }
        payroll.setOther(otherPay);
        payroll.setBonus(bonusPay);
        User dimissionUser = userService.getUserById(user.getId());
        SalaryHistory history = assymblyByPayRoll(payroll, loginUser);
        salaryHistoryService.save(history);
        dimissionUser.setDimissionDate(new Date());
        dimissionUser.setDimissionNote(note);
        userService.saveUser(dimissionUser);
        json.put("status", 200);
        return json.toJSONString();
    }

    public SalaryHistory assymblyByPayRoll(SalaryPayroll payroll, User entryUser) {
        Date now = new Date();
        SalaryHistory history = new SalaryHistory();
        history.setEntryUser(userService.getUserById(entryUser.getId()));
        history.setEntryDatetime(now);
        history.setBonus(payroll.getBonus());
        Double amount = 0.0;
        if (payroll.getLeave() != null) {
            history.setLeavePay(payroll.getLeave());
            amount -= payroll.getLeave();
        }
        history.setOtherPay(payroll.getOther());
        history.setSalary(salaryService.getSalaryByStaffId(payroll.getStaffId()));
        history.setSalaryDate(payroll.getSalaryDate());
        if (payroll.getBasic() !=null) {
            history.setTotal(payroll.getBasic());
            amount += payroll.getBasic();
        }
        amount = amount - payroll.getOther() + payroll.getBonus();
        if (amount < 0) {
            amount = 0.0;
        }
        history.setAmount(amount);
        return history;
    }
}
