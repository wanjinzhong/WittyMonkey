package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.City;

public interface ICityDao extends IGenericDao<City, Serializable> {

	/**
	 * 根据省获取所有市
	 * @param code
	 * @return
	 */
	List<City> getAllByProvince(Integer code);

	/**
	 * 根据市代码获取市
	 * @return
	 */
	City getCityByCode(Integer code);
}
