package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;

public interface IAreaService {
	
	public Area getAreaByCode(Integer code);
	
	public List<Area> getAllByCity(City city);
	
	public List<Area> getAllByCity(Integer code);
}
