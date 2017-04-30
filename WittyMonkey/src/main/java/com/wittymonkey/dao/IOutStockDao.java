package com.wittymonkey.dao;

import com.wittymonkey.entity.OutStock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IOutStockDao extends IGenericDao<OutStock, Serializable> {

    List<OutStock> getOutStockByMateriel(Integer materielId);

    Integer getTotal(Map<Integer, Object> param);

    List<OutStock> getOutStockByCondition(Map<Integer, Object> param, Integer start, Integer total);
}
