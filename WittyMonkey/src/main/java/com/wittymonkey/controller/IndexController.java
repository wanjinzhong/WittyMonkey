package com.wittymonkey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.Menu;
import com.wittymonkey.entity.Role;
import com.wittymonkey.entity.Setting;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by neilw on 2017/2/15.
 */
@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "getMenu", method = RequestMethod.GET)
    @ResponseBody
    public String getMenu(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        Set<Integer> menuId = new HashSet<Integer>();
        for (Role role : user.getRoles()) {
            for (Menu menu : role.getMenus()) {
                menuId.add(menu.getId());
            }
        }
        String lang = user.getSetting().getLang();
        Properties props = new Properties();
        JSONArray jsonArray = new JSONArray();
        try {
            props.load(IndexController.class.getResourceAsStream("/i18n/messages_" + lang + ".properties"));
            if (menuId.contains(1)) {
                // 添加楼层菜单
                JSONObject floorMenu = new JSONObject();
                floorMenu.put("title", props.getProperty("index.menu.floor"));
                floorMenu.put("icon", "#icon-mayishangchengdaizhifudengicon09");
                floorMenu.put("spread", false);
                floorMenu.put("href", "toFloorManage.do");
                jsonArray.add(floorMenu);
            }

            if (menuId.contains(2)) {
                // 添加房间菜单
                JSONObject roomMenu = new JSONObject();
                roomMenu.put("title", props.getProperty("index.menu.room"));
                roomMenu.put("icon", "#icon-fangjian");
                roomMenu.put("spread", false);
                roomMenu.put("href", "toRoomManage.do");
                jsonArray.add(roomMenu);
            }

            if (menuId.contains(3)) {
                //物料菜单
                JSONObject materielMenu = new JSONObject();
                materielMenu.put("title", props.getProperty("index.menu.materiel"));
                materielMenu.put("icon", "#icon-daoluputonghuowuyunshu");
                materielMenu.put("spread", false);
                JSONArray materielChildren = new JSONArray();
                //添加物料类型子菜单
                JSONObject materielType = new JSONObject();
                materielType.put("title", props.getProperty("index.menu.materiel.type"));
                materielType.put("href", "toMaterielType.do");
                materielChildren.add(materielType);
                materielMenu.put("children", materielChildren);
                jsonArray.add(materielMenu);
                //添加物料管理子菜单
                JSONObject materiel = new JSONObject();
                materiel.put("title", props.getProperty("index.menu.materiel"));
                materiel.put("href", "toMaterielManage.do");
                materielChildren.add(materiel);
            }

            if (menuId.contains(4)) {
                //库存菜单
                JSONObject inventoryMenu = new JSONObject();
                inventoryMenu.put("title", props.getProperty("index.menu.inventory"));
                inventoryMenu.put("icon", "#icon-kucun");
                inventoryMenu.put("spread", false);
                JSONArray inventoryChildren = new JSONArray();
                //入库子菜单
                JSONObject in = new JSONObject();
                in.put("title", props.getProperty("index.menu.inventory.in"));
                in.put("href", "toInventoryIn.do");
                inventoryChildren.add(in);
                //出库子菜单
                JSONObject out = new JSONObject();
                out.put("title", props.getProperty("index.menu.inventory.out"));
                out.put("href", "toInventoryOut.do");
                inventoryChildren.add(out);
                inventoryMenu.put("children", inventoryChildren);
                jsonArray.add(inventoryMenu);
            }

            if (menuId.contains(5)) {
                // 角色菜单
                JSONObject roleMenu = new JSONObject();
                roleMenu.put("title", props.getProperty("index.menu.role"));
                roleMenu.put("icon", "#icon-permission");
                roleMenu.put("spread", false);
                roleMenu.put("href", "toRoleManage.do");
                jsonArray.add(roleMenu);
            }

            if (menuId.contains(6)) {
                // 员工菜单
                JSONObject staffMenu = new JSONObject();
                staffMenu.put("title", props.getProperty("index.menu.staff"));
                staffMenu.put("icon", "#icon-yuangongguanli");
                staffMenu.put("spread", false);
                staffMenu.put("href", "toStaffManage.do");
                jsonArray.add(staffMenu);
            }

            if (menuId.contains(7)) {
                // 请假菜单
                JSONObject leaveMenu = new JSONObject();
                leaveMenu.put("title", props.getProperty("index.menu.leave"));
                leaveMenu.put("icon", "#icon-qingjia");
                leaveMenu.put("spread", false);
                JSONArray leaveChildren = new JSONArray();
                //请假类型子菜单
                JSONObject leaveChild = new JSONObject();
                leaveChild.put("title", props.getProperty("index.menu.leave.type"));
                leaveChild.put("href", "toLeaveType.do");
                leaveChildren.add(leaveChild);
                //请假管理子菜单
                JSONObject leaveType = new JSONObject();
                leaveType.put("title", props.getProperty("index.menu.leave"));
                leaveType.put("href", "toLeaveManage.do");
                leaveChildren.add(leaveType);
                leaveMenu.put("children", leaveChildren);
                jsonArray.add(leaveMenu);
            }

            if (menuId.contains(8)) {
                //财务菜单
                JSONObject financeMenu = new JSONObject();
                financeMenu.put("title", props.getProperty("index.menu.finance"));
                financeMenu.put("icon", "#icon-caiwu");
                financeMenu.put("spread", false);
                JSONArray financeChildren = new JSONArray();
                //财务类型
                JSONObject type = new JSONObject();
                type.put("title", props.getProperty("finance_type.title"));
                type.put("href", "toFinanceType.do");
                financeChildren.add(type);
                //收支登记子菜单
                JSONObject add = new JSONObject();
                add.put("title", props.getProperty("index.menu.finance.record"));
                add.put("href", "toFinanceRecord.do");
                financeChildren.add(add);
                //报销子菜单
                JSONObject reimburse = new JSONObject();
                reimburse.put("title", props.getProperty("index.menu.finance.reimburse"));
                reimburse.put("href", "toReimburseManage.do");
                financeChildren.add(reimburse);
                //员工
                JSONObject salary = new JSONObject();
                salary.put("title", props.getProperty("index.menu.finance.salary"));
                salary.put("href", "toFinanceSalary.do");
                financeChildren.add(salary);
                financeMenu.put("children", financeChildren);
                jsonArray.add(financeMenu);
            }

            if (menuId.contains(9)) {
                //报表
                JSONObject reportMenu = new JSONObject();
                reportMenu.put("title", props.getProperty("index.menu.report"));
                reportMenu.put("icon", "#icon-buchongiconsvg14");
                reportMenu.put("spread", false);
                reportMenu.put("href", "toReport.do");
                jsonArray.add(reportMenu);
            }

            // 个人中心
            JSONObject peronalMenu = new JSONObject();
            peronalMenu.put("title", props.getProperty("index.menu.personal"));
            peronalMenu.put("icon", "#icon-gerenzhongxin");
            peronalMenu.put("spread", false);
            JSONArray personalChildren = new JSONArray();
            //报销申请子菜单
            JSONObject reimbursApplyChild = new JSONObject();
            reimbursApplyChild.put("title", props.getProperty("index.menu.personal.reimburse"));
            reimbursApplyChild.put("href", "toReimburseApply.do");
            personalChildren.add(reimbursApplyChild);
            //请假申请子菜单
            JSONObject leaveApplyChild = new JSONObject();
            leaveApplyChild.put("title", props.getProperty("index.menu.personal.leave"));
            leaveApplyChild.put("href", "toLeaveApply.do");
            personalChildren.add(leaveApplyChild);
            peronalMenu.put("children", personalChildren);
            //我的工资子菜单
            JSONObject mySalaryChild = new JSONObject();
            mySalaryChild.put("title", props.getProperty("index.menu.personal.my_salary"));
            mySalaryChild.put("href", "toMySalary.do");
            personalChildren.add(mySalaryChild);
            peronalMenu.put("children", personalChildren);
            jsonArray.add(peronalMenu);

            //通知
            JSONObject notifyMenu = new JSONObject();
            notifyMenu.put("title", props.getProperty("index.menu.notify"));
            notifyMenu.put("icon", "#icon-tongzhi");
            notifyMenu.put("spread", false);
            notifyMenu.put("href", "toNotify.do");
            jsonArray.add(notifyMenu);

//            //信息
//            JSONObject infoMenu = new JSONObject();
//            infoMenu.put("title", props.getProperty("index.menu.information"));
//            infoMenu.put("icon", "#icon-info");
//            infoMenu.put("spread", false);
//            JSONArray infoChildren = new JSONArray();
//            //个人信息
//            JSONObject myInfo = new JSONObject();
//            myInfo.put("title", props.getProperty("index.menu.information.myinfo"));
//            myInfo.put("href", "toMyInfo.do");
//            infoChildren.add(myInfo);
//            //酒店信息
//            JSONObject hotelInfo = new JSONObject();
//            hotelInfo.put("title", props.getProperty("index.menu.information.hotelinfo"));
//            hotelInfo.put("href", "toHotelInfo.do");
//            infoChildren.add(hotelInfo);
//            infoMenu.put("children", infoChildren);
//            jsonArray.add(infoMenu);
            //设置
            JSONObject settingMenu = new JSONObject();
            settingMenu.put("title", props.getProperty("index.menu.settting"));
            settingMenu.put("icon", "#icon-shezhi");
            settingMenu.put("spread", false);
            settingMenu.put("href", "toSetting.do");
            jsonArray.add(settingMenu);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonArray.toJSONString();
    }

    @RequestMapping(value = "toMyDesk", method = RequestMethod.GET)
    public String toMyDesk(HttpServletRequest request) {
        return "my_desk";
    }

    @RequestMapping(value = "toFloorManage", method = RequestMethod.GET)
    public String toFloorManage(HttpServletRequest request) {
//        User user = userDao.getUserByLoginName("lyf");
//        request.getSession().setAttribute("loginUser", user);
//        request.getSession().setAttribute("hotel", user.getHotel());
        return "floor_manage";
    }

    @RequestMapping(value = "toRoomManage", method = RequestMethod.GET)
    public String toRoomManage(HttpServletRequest request) {
        /*User user = userDao.getUserByLoginName("lyf");
        request.getSession().setAttribute("loginUser", user);
        request.getSession().setAttribute("hotel", user.getHotel());*/
        return "room_manage";
    }

    @RequestMapping(value = "toChangeRoom", method = RequestMethod.GET)
    public String toChangeRoom(HttpServletRequest request) {
        return "change_room";
    }

    @RequestMapping(value = "toCheckOut", method = RequestMethod.GET)
    public String toCheckOut(HttpServletRequest request) {
        return "checkout";
    }

    @RequestMapping(value = "toMaterielManage", method = RequestMethod.GET)
    public String toMaterielManage(HttpServletRequest request) {
        return "materiel_manage";
    }

    @RequestMapping(value = "toMaterielType", method = RequestMethod.GET)
    public String toMaterielType(HttpServletRequest request) {
        return "materiel_type";
    }

    @RequestMapping(value = "toInventoryIn", method = RequestMethod.GET)
    public String toInventoryIn(HttpServletRequest request) {
        return "instock_manage";
    }

    @RequestMapping(value = "toInventoryOut", method = RequestMethod.GET)
    public String toInventoryOut(HttpServletRequest request) {
        return "outstock_manage";
    }

    @RequestMapping(value = "toStaffManage", method = RequestMethod.GET)
    public String toStaffManage(HttpServletRequest request) {
        return "staff_manage";
    }

    @RequestMapping(value = "toLeaveManage", method = RequestMethod.GET)
    public String toLeavemanage(HttpServletRequest request) {
        return "leave_manage";
    }

    @RequestMapping(value = "toLeaveType", method = RequestMethod.GET)
    public String toLeaveType(HttpServletRequest request) {
        return "leave_type";
    }

    @RequestMapping(value = "toFinanceRecord", method = RequestMethod.GET)
    public String toFinanceAdd(HttpServletRequest request) {
        return "finance_record";
    }

    @RequestMapping(value = "toReimburseManage", method = RequestMethod.GET)
    public String toFinanceReimburse(HttpServletRequest request) {
        return "reimburse_manage";
    }

    @RequestMapping(value = "toFinanceSalary", method = RequestMethod.GET)
    public String toFinanceSalary(HttpServletRequest request) {
        return "salary";
    }

    @RequestMapping(value = "toReport", method = RequestMethod.GET)
    public String toReport(HttpServletRequest request) {
        return "report";
    }

    @RequestMapping(value = "toNotify", method = RequestMethod.GET)
    public String toNotify(HttpServletRequest request) {
        return "notify";
    }

    @RequestMapping(value = "toMyInfo", method = RequestMethod.GET)
    public String toMyInfo(HttpServletRequest request) {
        return "my_info";
    }

    @RequestMapping(value = "toHotelInfo", method = RequestMethod.GET)
    public String toHotelInfo(HttpServletRequest request) {
        return "hotel_info";
    }

    @RequestMapping(value = "toSetting", method = RequestMethod.GET)
    public String toSetting(HttpServletRequest request) {
        return "setting";
    }

    @RequestMapping(value = "toReimburseApply", method = RequestMethod.GET)
    public String toReimburseApply(HttpServletRequest request){
        return "reimburse_apply";
    }

    @RequestMapping(value = "toLeaveApply", method = RequestMethod.GET)
    public String toLeaveApply(HttpServletRequest request){
        return "leave_apply";
    }

    @RequestMapping(value = "toDashboard", method = RequestMethod.GET)
    public String toDashboard(HttpServletRequest request){
        return "dashboard";
    }
}
