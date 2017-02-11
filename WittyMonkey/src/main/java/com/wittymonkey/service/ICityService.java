package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.City;
import com.wittymonkey.entity.Province;

public interface ICityService {

	public List<City> getAllByProvince(Province province);
	
	public List<City> getAllByProvince(Integer code);
}
