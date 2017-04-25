package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.InStock;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IInStockService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleInStock;
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
}
