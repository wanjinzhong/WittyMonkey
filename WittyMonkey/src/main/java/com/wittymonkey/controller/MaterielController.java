package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Materiel;
import com.wittymonkey.entity.MaterielType;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IMaterielService;
import com.wittymonkey.service.IMaterielTypeService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleMateriel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by neilw on 2017/4/23.
 */
@Controller
public class MaterielController {

    @Autowired
    private IMaterielService materielService;

    @Autowired
    private IMaterielTypeService materielTypeService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "getMaterielByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getMaterielByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer searchType = Integer.parseInt(request.getParameter("searchType"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer pageSize = loginUser.getSetting().getPageSize();
        Map<Integer, Object> condition = new HashMap<Integer, Object>();
        condition.put(Constraint.MATERIEL_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        Integer count = null;
        List<Materiel> materiels = null;
        if (Constraint.MATERIEL_SEARCHTYPE_TYPE.equals(searchType)) {
            Integer typeId = Integer.parseInt(request.getParameter("typeId"));
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_TYPE_ID, typeId);
        } else if (Constraint.MATERIEL_SEARCHTYPE_WARN.equals(searchType)) {
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_WARN, true);
        } else if (Constraint.MATERIEL_SEARCHTYPE_BARCODE.equals(searchType)) {
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_BARCODE, request.getParameter("barcode"));
        } else if (Constraint.MATERIEL_SEARCHTYPE_NAME.equals(searchType)) {
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_NAME, request.getParameter("name"));
        }
        count = materielService.getTotal(condition);
        materiels = materielService.getMaterielByPage(condition, (curr - 1) * pageSize, pageSize);
        List<SimpleMateriel> simpleMateriels = ChangeToSimple.materielList(materiels);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleMateriels);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toAddMateriel", method = RequestMethod.GET)
    public String toAddMateriel(HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<MaterielType> materielTypes = materielTypeService.getMaterielTypeByHotelId(hotel.getId(), null, null);
        request.setAttribute("types", materielTypes);
        return "materiel_add";
    }

    @RequestMapping(value = "validateBarcode", method = RequestMethod.GET)
    @ResponseBody
    public String validateBarcode(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String barcode = request.getParameter("barcode");
        String method = request.getParameter("method");
        if (Constraint.ADD.equals(method)) {
            json.put("status", validateBarcode(hotel.getId(), method, barcode, null));
        } else if (Constraint.UPDATE.equals(method)) {
            Materiel editMateriel = (Materiel) request.getSession().getAttribute("editMateriel");
            json.put("status", validateBarcode(hotel.getId(), method, barcode, editMateriel));
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "getNameByBarCode", method = GET)
    @ResponseBody
    public String getNameByBarCode(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String barcode = request.getParameter("barcode").trim();
        Materiel materiel = materielService.getMaterielByBarcode(hotel.getId(), barcode);
        if (materiel == null) {
            json.put("status", 400);
            return json.toJSONString();
        } else {
            json.put("status", 200);
            json.put("materiel", ChangeToSimple.materiel(materiel));
            return json.toJSONString();
        }
    }

    @RequestMapping(value = "getBarcodeByName", method = GET)
    @ResponseBody
    public String getBarcodeByName(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        String name = request.getParameter("name").trim();
        Materiel materiel = materielService.getMaterielByName(hotel.getId(), name);
        if (materiel == null) {
            json.put("status", 400);
            return json.toJSONString();
        } else {
            json.put("status", 200);
            json.put("materiel", ChangeToSimple.materiel(materiel));
            return json.toJSONString();
        }
    }

    /**
     * 验证物料条码号
     *
     * @param hotelId
     * @param method
     * @param barbode
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>条码号为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>条码号过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>条码号不存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>条码号已存在</td>
     * </tr>
     */
    public Integer validateBarcode(Integer hotelId, String method, String barbode, Materiel editMateriel) {
        barbode = barbode.trim();
        if (StringUtils.isBlank(barbode)) {
            return 400;
        } else if (barbode.length() > 50) {
            return 401;
        }
        Materiel materiel = materielService.getMaterielByBarcode(hotelId, barbode);
        if (materiel == null) {
            return 200;
        } else {
            if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method)
                    || (Constraint.UPDATE.equals(method) && !editMateriel.getId().equals(materiel.getId()))) {
                return 201;
            } else {
                return 200;
            }
        }
    }

    /**
     * 保存物料
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <td>400</td>
     * <td>条码号为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>条码号过长</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>条码号已存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>名称为空</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>名称过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>单位为空</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>单位过长</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>预警不正确</td>
     * </tr>
     * <tr>
     * <td>431</td>
     * <td>预警不能小于0</td>
     * </tr>
     * <tr>
     * <td>440</td>
     * <td>售价不正确</td>
     * </tr>
     * <tr>
     * <td>441</td>
     * <td>售价不能小于0</td>
     * </tr>
     * <tr>
     * <td>450</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveMateriel", method = POST)
    @ResponseBody
    public String saveMateriel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer valiMateriel = validateMateriel(request);
        if (!new Integer(200).equals(valiMateriel)) {
            json.put("status", valiMateriel);
            return json.toJSONString();
        }
        String barcode = request.getParameter("barcode").trim();
        String name = request.getParameter("name").trim();
        String unit = request.getParameter("unit").trim();
        String price = request.getParameter("sellPrice");
        Double sellPrice = 0.0;
        if (StringUtils.isNotBlank(price)){
            sellPrice = Double.parseDouble(price.trim());
        }
        Double warnStock = Double.parseDouble(request.getParameter("warnStock").trim());
        String note = request.getParameter("note").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer type = Integer.parseInt(request.getParameter("typeId"));
        Materiel materiel = new Materiel();
        materiel.setMaterielType(materielTypeService.getMaterielTypeById(type));
        materiel.setBarcode(barcode);
        materiel.setEntryDatetime(new Date());
        materiel.setEntryUser(userService.getUserById(user.getId()));
        materiel.setName(name);
        materiel.setNote(note);
        materiel.setSellPrice(sellPrice);
        materiel.setStock(0.0);
        materiel.setUnit(unit);
        materiel.setWarningStock(warnStock);
        materielService.saveMateriel(materiel);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "updateMateriel", method = POST)
    @ResponseBody
    public String updateMateriel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer valiMateriel = validateMateriel(request);
        if (!new Integer(200).equals(valiMateriel)) {
            json.put("status", valiMateriel);
            return json.toJSONString();
        }
        String barcode = request.getParameter("barcode").trim();
        String name = request.getParameter("name").trim();
        String unit = request.getParameter("unit").trim();
        String price = request.getParameter("sellPrice");
        Double sellPrice = 0.0;
        if (StringUtils.isNotBlank(price)){
            sellPrice = Double.parseDouble(price.trim());
        }
        Double warnStock = Double.parseDouble(request.getParameter("warnStock").trim());
        String note = request.getParameter("note").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer type = Integer.parseInt(request.getParameter("typeId"));
        Materiel materiel = (Materiel) request.getSession().getAttribute("editMateriel");
        materiel.setMaterielType(materielTypeService.getMaterielTypeById(type));
        materiel.setBarcode(barcode);
        materiel.setEntryDatetime(new Date());
        materiel.setEntryUser(userService.getUserById(user.getId()));
        materiel.setName(name);
        materiel.setNote(note);
        materiel.setSellPrice(sellPrice);
        materiel.setUnit(unit);
        materiel.setWarningStock(warnStock);
        materielService.saveMateriel(materiel);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "toMaterielDetail", method = RequestMethod.GET)
    public String toMaterielDetail(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        List<MaterielType> materielTypes = materielTypeService.getMaterielTypeByHotelId(hotel.getId(), null, null);
        request.setAttribute("types", materielTypes);
        Materiel materiel = materielService.getMaterielById(id);
        request.getSession().setAttribute("editMateriel", materiel);
        return "materiel_detail";
    }

    private Integer validateMateriel(HttpServletRequest request) {
        String barcode = request.getParameter("barcode").trim();
        String name = request.getParameter("name").trim();
        String unit = request.getParameter("unit").trim();
        String warnStock = request.getParameter("warnStock").trim();
        String sellPrice = request.getParameter("sellPrice").trim();
        String note = request.getParameter("note").trim();
        String method = request.getParameter("method").trim();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer valiBarcode = null;
        if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method)) {
            valiBarcode = validateBarcode(hotel.getId(), method, barcode, null);
        } else if (Constraint.UPDATE.equals(method)) {
            valiBarcode = validateBarcode(hotel.getId(), method, barcode, (Materiel) request.getSession().getAttribute("editMateriel"));
        }
        if (!new Integer(200).equals(valiBarcode)) {
            if (new Integer(201).equals(valiBarcode)) {
                valiBarcode = 402;
            }
            return valiBarcode;
        }
        if (StringUtils.isBlank(name)) {
            return 410;
        } else if (name.length() > 50) {
            return 411;
        }
        if (StringUtils.isBlank(unit)) {
            return 420;
        } else if (unit.length() > 10) {
            return 421;
        }
        Double warn = null;
        try {
            warn = Double.parseDouble(warnStock);
        } catch (NumberFormatException e) {
            return 430;
        }
        if (warn < 0) {
            return 431;
        }
        Double sell = 0.0;
        if (StringUtils.isNotBlank(sellPrice)) {
            try {
                sell = Double.parseDouble(sellPrice);
            } catch (NumberFormatException e) {
                return 440;
            }
            if (sell < 0) {
                return 441;
            }
        }
        if (StringUtils.isNotBlank(note) && note.length() > 1024) {
            return 450;
        }
        return 200;
    }

    @RequestMapping(value = "deleteMateriel", method = POST)
    @ResponseBody
    public String deleteMateriel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Materiel materiel = materielService.getMaterielById(id);
        if (materiel == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            materielService.deleteMateriel(materiel);
        } catch (SQLException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
        json.put("status", 200);
        return json.toJSONString();
    }
}