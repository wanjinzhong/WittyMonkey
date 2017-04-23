package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Materiel;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IMaterielService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by neilw on 2017/4/23.
 */
@Controller
public class MaterielController {

    @Autowired
    private IMaterielService materielService;

    @RequestMapping(value = "getMaterielByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getMaterielByPage(HttpServletRequest request){
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
        if (Constraint.MATERIEL_SEARCHTYPE_TYPE.equals(searchType)){
            Integer typeId = Integer.parseInt(request.getParameter("typeId"));
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_TYPE_ID, typeId);
        } else if (Constraint.MATERIEL_SEARCHTYPE_WARN.equals(searchType)){
            condition.put(Constraint.MATERIEL_SEARCH_CONDITION_WARN, true);
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
}
