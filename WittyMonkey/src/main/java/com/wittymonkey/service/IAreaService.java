package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;

public interface IAreaService {
	
	public Area getAreaByCode(String code);
	
	public List<Area> getAllByCity(City city);
}
