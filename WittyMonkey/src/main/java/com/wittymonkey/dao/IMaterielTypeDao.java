package com.wittymonkey.dao;

import com.wittymonkey.entity.MaterielType;

import java.io.Serializable;
import java.util.List;

public interface IMaterielTypeDao extends IGenericDao<MaterielType, Serializable> {

    List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize);

    Integer getTotalByHotelId(Integer hotelId);

    MaterielType getMaterielTypeByName(Integer hotelId, String name);

    MaterielType getMaterielTypeById(Integer id);

    MaterielType getDefaultByHotel(Integer hotelId);
}
