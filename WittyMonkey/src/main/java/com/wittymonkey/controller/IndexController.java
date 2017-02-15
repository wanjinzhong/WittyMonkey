package com.wittymonkey.controller;

import com.wittymonkey.dao.IHotelDao;
import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by neilw on 2017/2/15.
 */
@Controller
public class IndexController {

    @Autowired
    private IUserDao userDao;

    @RequestMapping(value = "toMyDesk", method = RequestMethod.GET)
    public String toMyDesk(HttpServletRequest request){
        return "my_desk";
    }

    @RequestMapping(value = "toFloorSummary", method = RequestMethod.GET)
    public String toFloorSummary(HttpServletRequest request){
        return "floor_summary";
    }

    @RequestMapping(value = "toFloorManage", method = RequestMethod.GET)
    public String toFloorManage(HttpServletRequest request){
        User user = userDao.getUserByLoginName("lyf");
        request.getSession().setAttribute("loginUser", user);
        request.getSession().setAttribute("hotel", user.getHotel());
        return "floor_manage";
    }

    @RequestMapping(value = "toRoomManage", method = RequestMethod.GET)
    public String toRoomManage(HttpServletRequest request){
        return "room_manage";
    }

    @RequestMapping(value = "toCheckIn", method = RequestMethod.GET)
    public String toCheckIn(HttpServletRequest request){
        return "check_in";
    }

    @RequestMapping(value = "toChangeRoom", method = RequestMethod.GET)
    public String toChangeRoom(HttpServletRequest request){
        return "change_room";
    }

    @RequestMapping(value = "toCheckOut", method = RequestMethod.GET)
    public String toCheckOut(HttpServletRequest request){
        return "check_out";
    }

    @RequestMapping(value = "toMaterielManage", method = RequestMethod.GET)
    public String toMaterielManage(HttpServletRequest request){
        return "materiel_manage";
    }

    @RequestMapping(value = "toMaterielType", method = RequestMethod.GET)
    public String toMaterielType(HttpServletRequest request){
        return "materiel_type";
    }

    @RequestMapping(value = "toInventoryIn", method = RequestMethod.GET)
    public String toInventoryIn(HttpServletRequest request){
        return "inventory_in";
    }

    @RequestMapping(value = "toInventoryOut", method = RequestMethod.GET)
    public String toInventoryOut(HttpServletRequest request){
        return "inventory_out";
    }

    @RequestMapping(value = "toStaffManage", method = RequestMethod.GET)
    public String toStaffManage(HttpServletRequest request){
        return "staff_manage";
    }

    @RequestMapping(value = "toLeaveManage", method = RequestMethod.GET)
    public String toLeavemanage(HttpServletRequest request){
        return "leave_manage";
    }

    @RequestMapping(value = "toLeaveType", method = RequestMethod.GET)
    public String toLeaveType(HttpServletRequest request){
        return "leave_type";
    }

    @RequestMapping(value = "toFinanceAdd", method = RequestMethod.GET)
    public String toFinanceAdd(HttpServletRequest request){
        return "finance_add";
    }

    @RequestMapping(value = "toFinanceReimburse", method = RequestMethod.GET)
    public String toFinanceReimburse(HttpServletRequest request){
        return "finance_reimburse";
    }

    @RequestMapping(value = "toFinanceSalaryChange", method = RequestMethod.GET)
    public String toFinanceSalaryChange(HttpServletRequest request){
        return "finance_salary_change";
    }

    @RequestMapping(value = "toFinanceSalary", method = RequestMethod.GET)
    public String toFinanceSalary(HttpServletRequest request){
        return "finance_salary";
    }

    @RequestMapping(value = "toReport", method = RequestMethod.GET)
    public String toReport(HttpServletRequest request){
        return "report";
    }

    @RequestMapping(value = "toNotify", method = RequestMethod.GET)
    public String toNotify(HttpServletRequest request){
        return "notify";
    }

    @RequestMapping(value = "toMyInfo", method = RequestMethod.GET)
    public String toMyInfo(HttpServletRequest request){
        return "my_info";
    }

    @RequestMapping(value = "toHotelInfo", method = RequestMethod.GET)
    public String toHotelInfo(HttpServletRequest request){
        return "hotel_info";
    }

    @RequestMapping(value = "toSetting", method = RequestMethod.GET)
    public String toSetting(HttpServletRequest request){
        return "setting";
    }
}
