package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.City;
import com.wittymonkey.entity.Province;

public interface ICityService {

	List<City> getAllByProvince(Province province);

	List<City> getAllByProvince(Integer code);

	City getCityByCode(Integer code);

}
