package com.wittymonkey.service;

import com.wittymonkey.entity.City;
import com.wittymonkey.entity.Province;

import java.util.List;

public interface ICityService {

    List<City> getAllByProvince(Province province);

    List<City> getAllByProvince(Integer code);

    City getCityByCode(Integer code);

}
