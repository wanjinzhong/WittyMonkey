package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.City;

public interface ICityDao extends IGenericDao<City, Serializable> {

	public List<City> getAllByProvince(Integer code);
}
