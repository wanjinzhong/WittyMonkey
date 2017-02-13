package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IAreaDao;
import com.wittymonkey.entity.Area;

@Repository(value = "areaDao")
public class AreaDaoImpl extends GenericDaoImpl<Area> implements IAreaDao {

	@Override
	public Area getAreaByCode(Integer code) {
		String hql = "from Area where code = :code";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		return queryOneHql(hql,param);
	}

	@Override
	public List<Area> getAllByCity(Integer code) {
		String hql = "from Area where city.code = :cityCode order by code asc";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("cityCode", code);
		return queryListHQL(hql, param);
	}

}
