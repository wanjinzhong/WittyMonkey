package com.wittymonkey.dao;

import com.wittymonkey.entity.Materiel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IMaterielDao extends IGenericDao<Materiel, Serializable> {
    List<Materiel> getMaterielByType(Integer typeId);

    Integer getTotal(Map<Integer, Object> condition);

    List<Materiel> getMaterielByPage(Map<Integer, Object> condition, Integer start, Integer total);

    Materiel getMaterielByBarcode(Integer hotelId, String barcode);

    Materiel getMaterielByName(Integer hotelId, String name);

    Materiel getMaterielById(Integer id);

    List<Materiel> getMaterielLowInventory(Integer hotelId);
}
