package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ICityDao;
import com.wittymonkey.entity.City;

@Repository(value="cityDao")
public class CityDaoImpl extends GenericDaoImpl<City> implements ICityDao{

	@Override
	public List<City> getAllByProvince(Integer code) {
		String hql = "from City where province.code = :provinceCode order by code asc";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("provinceCode", code);
		return queryHQL(hql, param);
	}

}
