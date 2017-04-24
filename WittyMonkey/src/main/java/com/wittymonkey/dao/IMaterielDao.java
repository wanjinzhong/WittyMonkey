package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.wittymonkey.entity.Materiel;

public interface IMaterielDao extends IGenericDao<Materiel, Serializable>{
    List<Materiel> getMaterielByType(Integer typeId);

    Integer getTotal(Map<Integer, Object> condition);

    List<Materiel> getMaterielByPage(Map<Integer, Object> condition, Integer start, Integer total);

    Materiel getMaterielByBarcode(Integer hotelId, String barcode);
}
