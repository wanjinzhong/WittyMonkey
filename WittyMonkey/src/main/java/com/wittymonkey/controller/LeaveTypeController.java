package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.LeaveType;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.ILeaveTypeService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleLeaveType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/26.
 */
@Controller
public class LeaveTypeController {

    @Autowired
    private ILeaveTypeService leaveTypeService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toAddLeaveType", method = RequestMethod.GET)
    public String toAddLeaveType(HttpServletRequest request) {
        return "leave_type_add";
    }

    @RequestMapping(value = "toEditLeaveType", method = RequestMethod.GET)
    public String toEditLeaveType(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        LeaveType leaveType = leaveTypeService.getLeaveTypeById(id);
        request.getSession().setAttribute("editLeaveType", leaveType);
        return "leave_type_edit";
    }

    @RequestMapping(value = "getLeaveTypeByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getLeaveTypeByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = leaveTypeService.getTotal(hotel.getId());
        List<LeaveType> leaveTypes = leaveTypeService.getLeaveTypes(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleLeaveType> simpleLeaveTypes = ChangeToSimple.leaveTypeList(leaveTypes);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleLeaveTypes);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "validateLeaveTypeName", method = RequestMethod.GET)
    @ResponseBody
    public String validateLeaveTypeName(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String method = request.getParameter("method").trim();
        String name = request.getParameter("name").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method)) {
            json.put("status", validateLeaveTypeName(hotel.getId(), method, name, null));
        }
        if (Constraint.UPDATE.equals(method)) {
            LeaveType leaveType = (LeaveType) request.getSession().getAttribute("editLeaveType");
            json.put("status", validateLeaveTypeName(hotel.getId(), method, name, leaveType));
        }
        return json.toJSONString();
    }

    /**
     * 验证类型名称
     *
     * @param hotelId
     * @param method
     * @param name
     * @param editLeaveType
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>名称为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>名称过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>类型不存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>类型存在</td>
     * </tr>
     */
    public Integer validateLeaveTypeName(Integer hotelId, String method, String name, LeaveType editLeaveType) {
        if (StringUtils.isBlank(name)) {
            return 400;
        }
        if (name.length() > 20) {
            return 401;
        }
        LeaveType leaveType = leaveTypeService.getLeaveTypeByName(hotelId, name);
        if (leaveType == null) {
            return 200;
        }
        if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method)
                || (Constraint.UPDATE.equals(method) && !leaveType.getId().equals(editLeaveType.getId()))) {
            return 201;
        } else {
            return 200;
        }
    }

    /**
     * 验证扣薪比
     *
     * @param deduct
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>扣薪比为空</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>扣薪比不正确</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>验证通过</td>
     * </tr>
     */
    public Integer validateDeduct(String deduct) {
        if (StringUtils.isBlank(deduct)) {
            return 410;
        }
        try {
            Double deductVal = Double.parseDouble(deduct);
            if (deductVal < 0 || deductVal > 100) {
                return 411;
            }
        } catch (NumberFormatException e) {
            return 411;
        }
        return 200;
    }

    /**
     * 保存请假类型
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>名称为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>名称过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>类型存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>扣薪比为空</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>扣薪比不正确</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveLeaveType", method = RequestMethod.POST)
    @ResponseBody
    public String saveLeaveType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String name = request.getParameter("name").trim();
        String deduct = request.getParameter("deduct").trim();
        String note = request.getParameter("note").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer valiName = validateLeaveTypeName(hotel.getId(), Constraint.ADD, name, null);
        if (!new Integer(200).equals(valiName)) {
            if (new Integer(201).equals(valiName)) {
                valiName = 402;
            }
            json.put("status", valiName);
            return json.toJSONString();
        }
        Integer valiDeduct = validateDeduct(deduct);
        if (!new Integer(200).equals(valiDeduct)) {
            json.put("status", valiDeduct);
            return json.toJSONString();
        }
        if (StringUtils.isNotBlank(note) && note.length() > 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        Double deductVal = Double.parseDouble(deduct)/100;
        LeaveType leaveType = new LeaveType();
        leaveType.setDeletable(true);
        leaveType.setHotel(hotelService.findHotelById(hotel.getId()));
        leaveType.setDeduct(deductVal);
        leaveType.setEntryDatetime(new Date());
        leaveType.setEntryUser(userService.getUserById(user.getId()));
        leaveType.setName(name);
        leaveType.setNote(note);
        leaveTypeService.saveLeaveType(leaveType);
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 保存请假类型
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>名称为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>名称过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>类型存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>扣薪比为空</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>扣薪比不正确</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>更新成功</td>
     * </tr>
     */
    @RequestMapping(value = "updateLeaveType", method = RequestMethod.POST)
    @ResponseBody
    public String updateLeaveType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String name = request.getParameter("name").trim();
        String deduct = request.getParameter("deduct").trim();
        String note = request.getParameter("note").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        LeaveType leaveType = (LeaveType) request.getSession().getAttribute("editLeaveType");
        Integer valiName = validateLeaveTypeName(hotel.getId(), Constraint.UPDATE, name, leaveType);
        if (!new Integer(200).equals(valiName)) {
            if (new Integer(201).equals(valiName)) {
                valiName = 402;
            }
            json.put("status", valiName);
            return json.toJSONString();
        }
        Integer valiDeduct = validateDeduct(deduct);
        if (!new Integer(200).equals(valiDeduct)) {
            json.put("status", valiDeduct);
            return json.toJSONString();
        }
        if (StringUtils.isNotBlank(note) && note.length() > 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        Double deductVal = Double.parseDouble(deduct)/100;
        LeaveType editLeaveType = leaveTypeService.getLeaveTypeById(leaveType.getId());
        editLeaveType.setDeduct(deductVal);
        editLeaveType.setEntryDatetime(new Date());
        editLeaveType.setEntryUser(userService.getUserById(user.getId()));
        editLeaveType.setName(name);
        editLeaveType.setNote(note);
        leaveTypeService.saveLeaveType(editLeaveType);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "deleteLeaveType", method = RequestMethod.POST)
    @ResponseBody
    public String deleteLeaveType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id"));
        LeaveType leaveType = leaveTypeService.getLeaveTypeById(id);
        if (leaveType == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            leaveTypeService.delete(leaveType);
        } catch (SQLException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        json.put("status", 200);
        return json.toJSONString();
    }
}
