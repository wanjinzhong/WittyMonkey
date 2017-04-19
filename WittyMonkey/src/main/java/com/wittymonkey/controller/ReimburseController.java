package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Reimburse;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.IReimburseService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleReimbuse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/18.
 */
@Controller
public class ReimburseController {

    @Autowired
    private IReimburseService reimburseService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IHotelService hotelService;

    @RequestMapping(value = "getReimburseByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getReimburseByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer status = Integer.parseInt(request.getParameter("type"));
        String fromStr = request.getParameter("from");
        String toStr = request.getParameter("to");
        Date from = null;
        Date to = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            from = dateFormat.parse(fromStr);
        } catch (ParseException e) {
        }
        try {
            to = dateFormat.parse(toStr);
        } catch (ParseException e) {
        }
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = reimburseService.getTotal(hotel.getId(), status, from, to);
        List<Reimburse> reimburses = reimburseService.getReimburseByPage(hotel.getId(), status, from, to, (curr - 1) * pageSize, pageSize);
        List<SimpleReimbuse> simpleReimbuses = ChangeToSimple.reimbuseList(reimburses);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleReimbuses);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddReimburse", method = RequestMethod.GET)
    public String toAddReimburse(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<User> users = userService.getUserByPage(hotel.getId(), Constraint.USER_INCUMBENT, null, null);
        request.setAttribute("users", users);
        return "reimburse_add";
    }

    /**
     * 保存报销
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>金额不正确</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>金额只能为正数</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>申请人备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>审核人备注过长</td>
     * </tr>
     */
    @RequestMapping(value = "saveReimburse", method = RequestMethod.POST)
    @ResponseBody
    public String saveReimburse(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer applyUser = Integer.parseInt(request.getParameter("applyUser"));
        String moneyStr = request.getParameter("money");
        Double money = null;
        Integer status = Integer.parseInt(request.getParameter("status"));
        String applyNote = request.getParameter("applyNote");
        String optNote = request.getParameter("optNote");
        try {
            money = Double.parseDouble(moneyStr);
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (money <= 0) {
            json.put("status", 401);
            return json.toJSONString();
        }
        if (StringUtils.isNotBlank(applyNote) && applyNote.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        if (status != 1 && StringUtils.isNotBlank(optNote) && optNote.length() > 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        Date now = new Date();
        Reimburse reimburse = new Reimburse();
        reimburse.setApplyDatetime(now);
        reimburse.setApplyUser(userService.getUserById(applyUser));
        reimburse.setApplyUserNote(applyNote);
        reimburse.setStatus(status);
        reimburse.setHotel(hotelService.findHotelById(hotel.getId()));
        reimburse.setMoney(money);
        if (status != 1) {
            reimburse.setEntryDatetime(now);
            reimburse.setEntryUser(userService.getUserById(loginUser.getId()));
            reimburse.setEntryUserNote(optNote);
        }
        reimburseService.save(reimburse);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "toShowReimburse", method = RequestMethod.GET)
    public String toShowReimburse(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Reimburse reimburse = reimburseService.getReimburseById(id);
        request.getSession().setAttribute("reimburse", reimburse);
        return "reimburse_detail";
    }


    /**
     * 批准或驳回报销
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>报销不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>报销已更新</td>
     * </tr>
     * <tr>
     * <td>422</td>
     * <td>已通过</td>
     * </tr>
     * <tr>
     * <td>423</td>
     * <td>已驳回</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>驳回成功</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>批准成功</td>
     * </tr>
     */
    @RequestMapping(value = "reimburseOperate", method = RequestMethod.POST)
    @ResponseBody
    public String reimburseOperate(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        JSONObject json = new JSONObject();
        Integer method = Integer.parseInt(request.getParameter("method"));
        Reimburse reimburse = (Reimburse) request.getSession().getAttribute("reimburse");
        String note = request.getParameter("note");
        if (StringUtils.isNotBlank(note) && note.length() > 1024) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Reimburse updateReimburse = reimburseService.getReimburseById(reimburse.getId());
        if (updateReimburse == null) {
            json.put("status", 401);
            return json.toJSONString();
        }
        if (!updateReimburse.getApplyDatetime().equals(reimburse.getApplyDatetime())) {
            json.put("status", 410);
            return json.toJSONString();
        }
        if (updateReimburse.getStatus() != 1) {
            json.put("status", 420 + updateReimburse.getStatus());
            json.put("optUser", updateReimburse.getEntryUser().getRealName());
            return json.toJSONString();
        }
        updateReimburse.setEntryUser(userService.getUserById(loginUser.getId()));
        updateReimburse.setEntryDatetime(new Date());
        updateReimburse.setEntryUserNote(note);
        updateReimburse.setStatus((Constraint.REIMBURSE_OPT_PASSE.equals(method) ? Constraint.REIMBURSE_STATUS_PASSED : Constraint.REIMBURSE_STATUS_REJECTED));
        reimburseService.save(updateReimburse);
        json.put("status", 200 + method);
        return json.toJSONString();
    }
}
