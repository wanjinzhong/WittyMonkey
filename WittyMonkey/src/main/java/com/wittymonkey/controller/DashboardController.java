package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SalaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by neilw on 2017/5/2.
 */
@Controller
public class DashboardController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private ICheckinService checkinService;

    @Autowired
    private IMaterielService materielService;

    @Autowired
    private ISalaryHistoryService salaryHistoryService;

    @Autowired
    private IReimburseService reimburseService;

    @Autowired
    private ILeaveHeaderService leaveHeaderService;

    @Autowired
    private ISalaryService salaryService;

    @RequestMapping(value = "getDashboard", method = RequestMethod.GET)
    @ResponseBody
    public String getDashboard(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Set<Integer> menus = new HashSet<Integer>();
        for (Role role : user.getRoles()) {
            for (Menu menu : role.getMenus()) {
                menus.add(menu.getId());
            }
        }
        Date now = new Date();
        // 房间
        if (menus.contains(2)) {
            JSONArray reserveArray = new JSONArray();
            JSONArray checkinArray = new JSONArray();
            List<Reserve> reserves = reserveService.getReservesViaEstCheckIn(hotel.getId(), now);
            for (Reserve reserve : reserves) {
                JSONObject reserveJson = new JSONObject();
                reserveJson.put("name", reserve.getCustomer().getName());
                reserveJson.put("room", reserve.getRoom().getNumber());
                reserveArray.add(reserveJson);
            }
            json.put("reserve", reserveArray);
            List<Checkin> checkins = checkinService.getCheckinViaEstCheckoutDate(hotel.getId(), now);
            for (Checkin checkin : checkins) {
                JSONObject checkinJson = new JSONObject();
                checkinJson.put("person", checkin.getCustomers().size());
                checkinJson.put("name", checkin.getCustomers().get(0).getName());
                checkinJson.put("room", checkin.getRoom().getNumber());
                checkinArray.add(checkinJson);
            }
            json.put("checkin", checkinArray);
        }
        // 库存
        if (menus.contains(4)) {
            List<Materiel> materiels = materielService.getMaterielLowInventory(hotel.getId());
            JSONArray zeroInventory = new JSONArray();
            JSONArray lowInventory = new JSONArray();
            for (Materiel materiel : materiels) {
                // 0库存
                if (materiel.getStock() == 0) {
                    JSONObject zero = new JSONObject();
                    zero.put("name", materiel.getName());
                    zero.put("barcode", materiel.getBarcode());
                    zeroInventory.add(zero);
                }
                // 底库存
                else {
                    JSONObject low = new JSONObject();
                    low.put("name", materiel.getName());
                    low.put("barcode", materiel.getBarcode());
                    lowInventory.add(low);
                }
            }
            json.put("zeroInventory", zeroInventory);
            json.put("lowInventory", lowInventory);
        }
        // 工资和报销
        if (menus.contains(8)) {
            JSONObject salary = new JSONObject();
            List<User> satffs = userService.getUserByPage(hotel.getId(), Constraint.USER_INCUMBENT, null, null);
            Date lastMonth = DateUtil.lastMonthFirstDate(now);
            StringBuilder name = new StringBuilder();
            Integer number = 0;
            for (User staff : satffs) {
                SalaryHistory history = salaryHistoryService.getSalaryHistoryByUserIdAndSalaryDate(staff.getId(), lastMonth);
                if (history == null) {
                    Salary mySalary = salaryService.getSalaryByStaffId(staff.getId());
                    SalaryVO salaryVO = ChangeToSimple.convertSalaryByTime(mySalary, lastMonth);
                    if (salaryVO != null && salaryVO.getMoney() != null) {
                        if (number == 0) {
                            name.append(staff.getRealName());
                        } else if (number < 3) {
                            name.append(", " + staff.getRealName());
                        }
                        number++;
                    }
                }
            }
            salary.put("name", name.toString());
            salary.put("number", number);
            json.put("salary", salary);

            List<Reimburse> reimburses = reimburseService.getReimburseByPage(hotel.getId(), Constraint.REIMBURSE_STATUS_PENDING, null, null, null, null);
            JSONArray reimburseArray = new JSONArray();
            Map<Integer, Map<String, Object>> reimburseMap = new HashMap<Integer, Map<String, Object>>();
            for (Reimburse reimburse : reimburses) {
                Map<String, Object> content = null;
                if (reimburseMap.containsKey(reimburse.getApplyUser().getId())) {
                    content = reimburseMap.get(reimburse.getApplyUser().getId());
                    content.put("number", (Integer) content.get("number") + 1);

                } else {
                    content = new HashMap<String, Object>();
                    content.put("name", reimburse.getApplyUser().getRealName());
                    content.put("number", 1);
                }

                reimburseMap.put(reimburse.getApplyUser().getId(), content);
            }
            for (Map.Entry entry : reimburseMap.entrySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", ((Map) entry.getValue()).get("name"));
                jsonObject.put("number", ((Map) entry.getValue()).get("number"));
                reimburseArray.add(jsonObject);
            }
            json.put("reimburse", reimburseArray);
        }
        // 请假
        if (menus.contains(7)) {
            List<LeaveHeader> leaveHeaders = leaveHeaderService.getLeaveHeaderByStatus(hotel.getId(), Constraint.LEAVE_STATUS_PENDING, null, null);
            JSONArray leaveArray = new JSONArray();
            Map<Integer, Map<String, Object>> leaveMap = new HashMap<Integer, Map<String, Object>>();
            for (LeaveHeader header : leaveHeaders) {
                Map<String, Object> content = null;
                if (leaveMap.containsKey(header.getApplyUser().getId())) {
                    content = leaveMap.get(header.getApplyUser().getId());
                    content.put("number", (Integer) content.get("number") + 1);

                } else {
                    content = new HashMap<String, Object>();
                    content.put("name", header.getApplyUser().getRealName());
                    content.put("number", 1);
                }

                leaveMap.put(header.getApplyUser().getId(), content);
            }
            for (Map.Entry entry : leaveMap.entrySet()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", ((Map) entry.getValue()).get("name"));
                jsonObject.put("number", ((Map) entry.getValue()).get("number"));
                leaveArray.add(jsonObject);
            }
            json.put("leave", leaveArray);
        }
        return json.toJSONString();
    }
}
