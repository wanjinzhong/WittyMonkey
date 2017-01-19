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
	public Area getAreaByCode(String code) {
		Area area = null;
		String hql = "from Area where code = :code";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		List<Area> areas = this.queryHQL(hql, map);
		if (areas != null && areas.size() > 0){
			area =  this.queryHQL(hql, map).get(0);
		}
		return area;
	}

}