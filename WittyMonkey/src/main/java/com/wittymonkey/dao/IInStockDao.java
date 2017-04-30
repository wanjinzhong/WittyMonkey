package com.wittymonkey.dao;

import com.wittymonkey.entity.InStock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IInStockDao extends IGenericDao<InStock, Serializable> {

    List<InStock> getInStockByMateriel(Integer materielId);

    Integer getTotal(Map<Integer, Object> param);

    List<InStock> getInStockByCondition(Map<Integer, Object> param, Integer start, Integer total);
}
