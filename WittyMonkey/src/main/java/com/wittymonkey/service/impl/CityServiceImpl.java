package com.wittymonkey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wittymonkey.dao.ICityDao;
import com.wittymonkey.entity.City;
import com.wittymonkey.entity.Province;
import com.wittymonkey.service.ICityService;

@Service(value = "cityService")
public class CityServiceImpl implements ICityService {

	@Autowired
	private ICityDao cityDao;

	@Override
	public List<City> getAllByProvince(Province province) {
		return cityDao.getAllByProvince(province.getCode());
	}

	@Override
	public List<City> getAllByProvince(Integer code) {
		return cityDao.getAllByProvince(code);
	}

	@Override
	public City getCityByCode(Integer code) {
		return cityDao.getCityByCode(code);
	}

}
