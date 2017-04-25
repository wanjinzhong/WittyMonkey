package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleInStock;
import com.wittymonkey.vo.SimpleOutStock;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by neilw on 2017/4/25.
 */
@Controller
public class InventoryController {

    @Autowired
    private IInStockService inStockService;

    @Autowired
    private IOutStockService outStockService;

    @Autowired
    private IMaterielService materielService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IHotelService hotelService;

    @RequestMapping(value = "getInstockByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getInstockByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer searchType = Integer.parseInt(request.getParameter("searchType"));
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(Constraint.INSTOCK_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dateFrom = request.getParameter("from");
            if (StringUtils.isNotBlank(dateFrom)) {
                Date from = dateFormat.parse(dateFrom);
                param.put(Constraint.INSTOCK_SEARCH_CONDITION_FROM, from);
            }
        } catch (NullPointerException e) {
        } catch (ParseException e) {
        }
        try {
            String dateTo = request.getParameter("to");
            if (StringUtils.isNotBlank(dateTo)) {
                Date to = dateFormat.parse(dateTo);
                param.put(Constraint.INSTOCK_SEARCH_CONDITION_TO, to);
            }
        } catch (NullPointerException e) {
        } catch (ParseException e) {
        }
        if (Constraint.INSTOCK_SEARCHTYPE_TYPE.equals(searchType)) {
            Integer type = Integer.parseInt(request.getParameter("type"));
            param.put(Constraint.INSTOCK_SEARCH_CONDITION_TYPE_ID, type);
        } else if (Constraint.INSTOCK_SEARCHTYPE_BARCODE.equals(searchType)) {
            String barcode = request.getParameter("barcode");
            param.put(Constraint.INSTOCK_SEARCH_CONDITION_BARCODE, barcode);
        } else if (Constraint.INSTOCK_SEARCHTYPE_NAME.equals(searchType)) {
            String name = request.getParameter("name");
            param.put(Constraint.INSTOCK_SEARCH_CONDITION_NAME, name);
        }
        Integer count = inStockService.getTotal(param);
        List<InStock> inStocks = inStockService.getInStocks(param, (curr - 1) * pageSize, pageSize);
        List<SimpleInStock> simpleInStocks = ChangeToSimple.inStockList(inStocks);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleInStocks);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "getOutStockByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getOutStockByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer searchType = Integer.parseInt(request.getParameter("searchType"));
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dateFrom = request.getParameter("from");
            if (StringUtils.isNotBlank(dateFrom)) {
                Date from = dateFormat.parse(dateFrom);
                param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_FROM, from);
            }
        } catch (NullPointerException e) {
        } catch (ParseException e) {
        }
        try {
            String dateTo = request.getParameter("to");
            if (StringUtils.isNotBlank(dateTo)) {
                Date to = dateFormat.parse(dateTo);
                param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_TO, to);
            }
        } catch (NullPointerException e) {
        } catch (ParseException e) {
        }
        if (Constraint.OUTSTOCK_SEARCHTYPE_TYPE.equals(searchType)) {
            Integer type = Integer.parseInt(request.getParameter("type"));
            param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_TYPE_ID, type);
        } else if (Constraint.OUTSTOCK_SEARCHTYPE_BARCODE.equals(searchType)) {
            String barcode = request.getParameter("barcode");
            param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_BARCODE, barcode);
        } else if (Constraint.OUTSTOCK_SEARCHTYPE_NAME.equals(searchType)) {
            String name = request.getParameter("name");
            param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_NAME, name);
        }
        Integer count = outStockService.getTotal(param);
        List<OutStock> outStocks = outStockService.getOutStocks(param, (curr - 1) * pageSize, pageSize);
        List<SimpleOutStock> simpleOutStocks = ChangeToSimple.outStockList(outStocks);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleOutStocks);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toInStock", method = RequestMethod.GET)
    public String toInStock(HttpServletRequest request){
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(Constraint.MATERIEL_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        List<Materiel> materiels = materielService.getMaterielByPage(map, null, null);
        request.setAttribute("materiels", materiels);
        return "in_stock";
    }
    @RequestMapping(value = "toOutStock", method = RequestMethod.GET)
    public String toIOutStock(HttpServletRequest request){
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(Constraint.MATERIEL_SEARCH_CONDITION_HOTEL_ID, hotel.getId());
        List<Materiel> materiels = materielService.getMaterielByPage(map, null, null);
        request.setAttribute("materiels", materiels);
        return "out_stock";
    }

    /**
     * 入库
     * @param request
     * @return
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>物料不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>价格为空</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>价格不正确</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>数量为空</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>数量不正确</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>总价为空</td>
     * </tr>
     * <tr>
     * <td>431</td>
     * <td>总价不正确</td>
     * </tr>
     * <tr>
     * <td>440</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>入库成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveInStock", method = RequestMethod.POST)
    @ResponseBody
    public String saveInStock(HttpServletRequest request){
        JSONObject json = new JSONObject();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        String barcode = request.getParameter("barcode").trim();
        Materiel materiel = materielService.getMaterielByBarcode(hotel.getId(), barcode);
        if (materiel == null){
            json.put("status", 400);
            return json.toJSONString();
        }
        Double price = null;
        try{
            price = Double.parseDouble(request.getParameter("price"));
        } catch (NullPointerException e){
          json.put("status", 410);
          return json.toJSONString();
        } catch (NumberFormatException e){
            json.put("status", 411);
            return json.toJSONString();
        }
        if (price < 0){
            json.put("status", 411);
            return json.toJSONString();
        }
        Double qty = null;
        try{
            qty = Double.parseDouble(request.getParameter("qty"));
        } catch (NullPointerException e){
            json.put("status", 420);
            return json.toJSONString();
        } catch (NumberFormatException e){
            json.put("status", 421);
            return json.toJSONString();
        }
        if (qty <= 0){
            json.put("status", 421);
            return json.toJSONString();
        }
        Double pay = null;
        try{
            pay = Double.parseDouble(request.getParameter("pay"));
        } catch (NullPointerException e){
            json.put("status", 430);
            return json.toJSONString();
        } catch (NumberFormatException e){
            json.put("status", 431);
            return json.toJSONString();
        }
        if (pay < 0){
            json.put("status", 431);
            return json.toJSONString();
        }
        String note = request.getParameter("note");
        if (StringUtils.isNotBlank(note) && note.length() > 1024){
            json.put("status", 440);
            return json.toJSONString();
        }
        materiel.setStock(materiel.getStock() + qty);
        InStock inStock = new InStock();
        inStock.setEntryDatetime(new Date());
        inStock.setEntryUser(userService.getUserById(user.getId()));
        inStock.setMateriel(materiel);
        inStock.setHotel(hotelService.findHotelById(hotel.getId()));
        inStock.setNote(note);
        inStock.setPayment(pay);
        inStock.setPurchasePrice(price);
        inStock.setQuantity(qty);
        inStockService.save(inStock);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "saveOutStock")
    @ResponseBody
    public String saveOutStock(HttpServletRequest request){
        JSONObject json = new JSONObject();
        return json.toJSONString();

    }
}
