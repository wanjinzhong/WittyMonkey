package com.wittymonkey.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;
import com.wittymonkey.service.IAreaService;
import com.wittymonkey.service.ICityService;
import com.wittymonkey.service.IProvinceService;
import com.wittymonkey.vo.SimplePlace;


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
		List<SimplePlace> simplePlaces = new ArrayList<SimplePlace>();
		for (int i = 0; i < cities.size(); i ++){
			SimplePlace simpleCity = new SimplePlace();
			simpleCity.setCode(cities.get(i).getCode());
			simpleCity.setName(cities.get(i).getName());
			simplePlaces.add(simpleCity);
		}
		jsonArray.addAll(simplePlaces);
		return jsonArray.toJSONString();		
	}
	
	@RequestMapping(value = "getAreaByCity", method = RequestMethod.GET)
	@ResponseBody
	public String getAreaByCity(HttpServletRequest request) {
		JSONArray jsonArray = new JSONArray();
		Integer cityCode = Integer.parseInt(request.getParameter("code"));
		List<Area> areas = areaService.getAllByCity(cityCode);
		List<SimplePlace> simplePlaces = new ArrayList<SimplePlace>();
		for (int i = 0; i < areas.size(); i ++){
			SimplePlace simpleCity = new SimplePlace();
			simpleCity.setCode(areas.get(i).getCode());
			simpleCity.setName(areas.get(i).getName());
			simplePlaces.add(simpleCity);
		}
		jsonArray.addAll(simplePlaces);
		return jsonArray.toJSONString();		
	}
}
