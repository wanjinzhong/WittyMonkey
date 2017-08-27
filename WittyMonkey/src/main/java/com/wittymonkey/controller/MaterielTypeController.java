package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.MaterielType;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.IMaterielTypeService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleMaterielType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by neilw on 2017/3/25.
 */
@Controller
public class MaterielTypeController {

    @Autowired
    private IMaterielTypeService materielTypeService;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toEditMaterielType", method = GET)
    public String toEditMaterielType(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("typeId"));
        MaterielType materielType = materielTypeService.getMaterielTypeById(id);
        request.getSession().setAttribute("editMaterielType", materielType);
        return "materiel_type_edit";
    }

    @RequestMapping(value = "toAddMaterielType", method = GET)
    public String toAddMaterielType(HttpServletRequest request) {
        return "materiel_type_add";
    }

    @RequestMapping(value = "getMaterialTypeByPage", method = GET)
    @ResponseBody
    public String getMaterialTypeByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = materielTypeService.getTotalByHotelId(hotel.getId());
        List<MaterielType> materielTypes = materielTypeService.getMaterielTypeByHotelId(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleMaterielType> simpleMaterielTypes = ChangeToSimple.materielTypeList(materielTypes);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleMaterielTypes);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "getAllMaterielTypeByHotel", method = GET)
    @ResponseBody
    public String getAllMaterielTypeByHotel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<MaterielType> materielTypes = materielTypeService.getMaterielTypeByHotelId(hotel.getId(), null, null);
        List<SimpleMaterielType> simpleMaterielTypes = ChangeToSimple.materielTypeList(materielTypes);
        JSONArray array = new JSONArray();
        array.addAll(simpleMaterielTypes);
        json.put("data", array);
        return array.toJSONString();
    }

    @RequestMapping(value = "deleteMaterielType", method = POST)
    @ResponseBody
    public String deleteMaterielType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("typeId"));
        MaterielType materielType = materielTypeService.getMaterielTypeById(id);
        if (materielType == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            materielTypeService.deleteMaterielType(materielType);
        } catch (SQLException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 验证物料类型名称
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>物料类型不存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>物料类型存在</td>
     * </tr>
     */
    @RequestMapping(value = "validateMaterielTypeName", method = GET)
    @ResponseBody
    public String validateMaterielTypeName(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String method = request.getParameter("method");
        String name = request.getParameter("name").trim();
        json.put("status", validateMaterielTypeName(request, method, name));
        return json.toJSONString();
    }

    /**
     * 验证物料类型
     *
     * @param
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>物料类型存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>物料类型不存在</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有输入</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     * </table>
     */
    public Integer validateMaterielTypeName(HttpServletRequest request, String method, String typeName) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String editTypeName = null;
        if (Constraint.UPDATE.equals(method)) {
            editTypeName = ((MaterielType) request.getSession().getAttribute("editMaterielType")).getName();
        }
        if (StringUtils.isBlank(typeName)) {
            return 400;
        }
        MaterielType materielType = materielTypeService.getMaterielTypeByName(hotel.getId(), typeName);
        if (materielType == null) {
            return 201;
        }
        if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method)
                || (Constraint.UPDATE.equals(method) && !editTypeName.equals(typeName))) {
            return 200;
        }
        return 201;
    }

    /**
     * 保存物料类型
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>姓名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>姓名过长</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>物料类型已存在</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveMaterielType", method = GET)
    @ResponseBody
    public String saveMaterielType(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        JSONObject json = new JSONObject();
        String method = request.getParameter("method");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        if (StringUtils.isBlank(name)) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (name.length() > 10) {
            json.put("status", 401);
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        if (validateMaterielTypeName(request, method, name) == 200) {
            json.put("status", 420);
            return json.toJSONString();
        }
        MaterielType materielType = new MaterielType();
        materielType.setHotel(hotelService.findHotelById(hotel.getId()));
        materielType.setName(name);
        materielType.setNote(note);
        materielType.setEntryUser(userService.getUserById(user.getId()));
        materielType.setEntryDatetime(new Date());
        materielType.setEditable(true);
        materielType.setDefault(false);
        materielTypeService.saveMaterielType(materielType);

        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 更新物料类型
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>姓名为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>姓名过长</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>物料类型已存在</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>更新成功</td>
     * </tr>
     */
    @RequestMapping(value = "updateMaterielType", method = POST)
    @ResponseBody
    public String updateMaterielType(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        MaterielType materielType = (MaterielType) request.getSession().getAttribute("editMaterielType");
        String method = request.getParameter("method");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        if (StringUtils.isBlank(name)) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (name.length() > 10) {
            json.put("status", 401);
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 410);
            return json.toJSONString();
        }
        if (validateMaterielTypeName(request, method, name) == 200) {
            json.put("status", 420);
            return json.toJSONString();
        }
        MaterielType type = materielTypeService.getMaterielTypeById(materielType.getId());
        type.setName(name);
        type.setNote(note);
        type.setEntryDatetime(new Date());
        type.setEntryUser(userService.getUserById(user.getId()));
        materielTypeService.saveMaterielType(type);
        json.put("status", 200);
        return json.toJSONString();

    }
}
