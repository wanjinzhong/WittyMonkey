package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;
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
	
	/**
	 * 根据城市获取所有地区
	 * @param city
	 * @return
	 */
	public List<Area> getAllByCity(City city);
}
