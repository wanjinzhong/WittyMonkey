package com.wittymonkey.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constriant;
import com.wittymonkey.vo.SimpleFinanceType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/14.
 */
@Controller
public class FinanceTypeController {

    @Autowired
    private IFinanceTypeService financeTypeService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toFinanceType", method = RequestMethod.GET)
    public String toFinanceType(HttpServletRequest request) {
        return "finance_type";
    }

    @RequestMapping(value = "getFinanceTypeByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getFinanceTypeByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = financeTypeService.getTotal(hotel.getId());
        List<FinanceType> financeTypes = financeTypeService.getFinanceTypeByPage(hotel.getId(), type, (curr - 1) * pageSize, pageSize);
        List<SimpleFinanceType> simpleFinanceTypes = ChangeToSimple.financeTypeList(financeTypes);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleFinanceTypes);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddFinanceType", method = RequestMethod.GET)
    public String toAddFinanceType(HttpServletRequest request) {
        return "finance_type_add";
    }

    @RequestMapping(value = "saveFinanceType", method = RequestMethod.GET)
    @ResponseBody
    public String saveFinanceType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String name = request.getParameter("name");
        Integer type = null;
        try {
            type = Integer.parseInt(request.getParameter("type"));
        } catch (NumberFormatException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        String note = request.getParameter("note");
        Integer nameVali = validateFinanceTypeName(request, Constriant.ADD, name);
        if (!new Integer(201).equals(nameVali)) {
            if (new Integer(200).equals(nameVali)) {
                json.put("status", 402);
            } else {
                json.put("status", nameVali);
            }
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        FinanceType financeType = new FinanceType();
        financeType.setName(name);
        financeType.setNote(note);
        financeType.setHotel(hotelService.findHotelById(hotel.getId()));
        financeType.setEntryDatetime(new Date());
        financeType.setEntryUser(userService.getUserById(loginUser.getId()));
        financeType.setIncome(type == 1);
        financeType.setEditable(true);
        financeTypeService.save(financeType);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "validateFinanceTypeName", method = RequestMethod.GET)
    @ResponseBody
    public String validateFinanceTypeName(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String name = request.getParameter("name");
        String type = request.getParameter("method");
        json.put("status", validateFinanceTypeName(request, type, name));
        return json.toJSONString();
    }

    /**
     * 验证类型名
     *
     * @param type
     * @param name
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>类型名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>类型名过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>类型名存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>类型名不存在</td>
     * </tr>
     */
    public Integer validateFinanceTypeName(HttpServletRequest request, String type, String name) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String editFianceType = null;
        if (StringUtils.isBlank(name)) {
            return 400;
        }
        if (name.trim().length() > 10) {
            return 401;
        }
        if (Constriant.UPDATE.equals(type)) {
            editFianceType = ((FinanceType) request.getSession().getAttribute("editFinanceType")).getName();
        }
        FinanceType financeType = financeTypeService.getFinanceTypeByName(hotel.getId(), name);

        if (financeType != null) {
            if (Constriant.ADD.equals(type) || Constriant.DELETE.equals(type)
                    || (Constriant.UPDATE.equals(type) && !editFianceType.equals(name))) {
                return 200;
            } else {
                return 201;
            }
        } else {
            return 201;
        }
    }

    @RequestMapping(value = "toEditFinanceType", method = RequestMethod.GET)
    public String toEditFinanceType(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        FinanceType financeType = financeTypeService.getFinanceTypeById(id);
        request.getSession().setAttribute("editFinanceType", financeType);
        return "finance_type_edit";
    }

    @RequestMapping(value = "updateFinanceType", method = RequestMethod.POST)
    @ResponseBody
    public String updateFinanceType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String name = request.getParameter("name");
        Integer type = null;
        try {
            type = Integer.parseInt(request.getParameter("type"));
        } catch (NumberFormatException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        String note = request.getParameter("note");
        Integer nameVali = validateFinanceTypeName(request, Constriant.UPDATE, name);
        if (!new Integer(201).equals(nameVali)) {
            if (new Integer(200).equals(nameVali)) {
                json.put("status", 402);
            } else {
                json.put("status", nameVali);
            }
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        FinanceType financeType = financeTypeService.getFinanceTypeById(
                ((FinanceType) request.getSession().getAttribute("editFinanceType")).getId());
        if (financeType == null) {
            json.put("status", 420);
            return json.toJSONString();
        }
        if (!financeType.getEditable()) {
            json.put("status", 430);
            return json.toJSONString();
        }
        financeType.setName(name);
        financeType.setNote(note);
        financeType.setHotel(hotelService.findHotelById(hotel.getId()));
        financeType.setEntryDatetime(new Date());
        financeType.setEntryUser(userService.getUserById(loginUser.getId()));
        financeType.setIncome(type == 1);
        financeTypeService.save(financeType);
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 删除财务类型
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
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>删除成功</td>
     * </tr>
     */
    @RequestMapping(value = "deleteFinanceType", method = RequestMethod.POST)
    @ResponseBody
    public String deleteFinanceType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        FinanceType financeType = financeTypeService.getFinanceTypeById(id);
        if (financeType == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            financeTypeService.delete(financeType, hotel.getId(), loginUser.getId());
        } catch (SQLException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "getFinanceTypeByType", method = RequestMethod.GET)
    @ResponseBody
    public String getFinanceTypeByType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer type = Integer.parseInt(request.getParameter("type"));
        List<FinanceType> financeTypes = financeTypeService.getFinanceTypeByPage(hotel.getId(), type, null, null);
        List<SimpleFinanceType> simpleFinanceTypes = ChangeToSimple.financeTypeList(financeTypes);
        json.put("data", simpleFinanceTypes);
        return json.toJSONString();
    }
}
