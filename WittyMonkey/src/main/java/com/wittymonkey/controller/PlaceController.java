package com.wittymonkey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.wittymonkey.entity.City;
import com.wittymonkey.service.IAreaService;
import com.wittymonkey.service.ICityService;
import com.wittymonkey.service.IProvinceService;


@Controller
public class PlaceController {

	@Autowired
	private IProvinceService provinceService;

	@Autowired
	private ICityService cityService;

	@Autowired
	private IAreaService areaService;

	@RequestMapping(value = "getCityByProvince", method = RequestMethod.GET)
	@ResponseBody
	public String getCityByProvince(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Integer provinceCode = Integer.parseInt(request.getParameter("code"));
		List<City> cities = cityService.getAllByProvince(provinceCode);
		jsonArray.addAll(cities);
		return jsonArray.toString();		
	}
}
