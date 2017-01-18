package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IAreaDao;
import com.wittymonkey.entity.Area;

@Repository(value = "areaDao")
public class AreaDaoImpl extends GenericDaoImpl<Area> implements IAreaDao {

	@Override
	public Area getAreaByCode(String code) {
		Area area = new Area();
		String hql = "from Area where Area.code = :code";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		area = (Area) this.queryHQL(hql, map);
		return area;
	}

}
