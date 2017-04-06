package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wittymonkey.entity.MaterielType;

public interface IMaterielTypeDao extends IGenericDao<MaterielType, Serializable>{

    List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize);

    Integer getTotalByHotelId(Integer hotelId);

    MaterielType getMaterielTypeByName(Integer hotelId, String name);
}
