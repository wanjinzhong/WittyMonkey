package com.wittymonkey.dao;

import java.io.Serializable;

import com.wittymonkey.entity.Area;
/**
 * 
 * @author neilw
 *
 */
public interface IAreaDao extends IGenericDao<Area, Serializable>{
	/**
	 * 根据area_code获取Area(包括对应的city和province)
	 * @param code
	 * @return
	 */
	public Area getAreaByCode(String code);
}
