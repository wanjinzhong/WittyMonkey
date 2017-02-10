package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ICityDao;
import com.wittymonkey.entity.City;

@Repository(value="cityDao")
public class CityDaoImpl extends GenericDaoImpl<City> implements ICityDao{

}
