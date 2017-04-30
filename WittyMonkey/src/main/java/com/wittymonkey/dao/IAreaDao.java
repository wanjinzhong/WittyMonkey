package com.wittymonkey.dao;

import com.wittymonkey.entity.Area;

import java.io.Serializable;
import java.util.List;

/**
 * @author neilw
 */
public interface IAreaDao extends IGenericDao<Area, Serializable> {
    /**
     * 根据area_code获取Area(包括对应的city和province)
     *
     * @param code
     * @return
     */
    Area getAreaByCode(Integer code);

    /**
     * 根据城市获取所有地区
     *
     * @param code
     * @return
     */
    List<Area> getAllByCity(Integer code);
}
