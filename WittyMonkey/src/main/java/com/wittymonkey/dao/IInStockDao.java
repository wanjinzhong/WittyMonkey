package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.InStock;

public interface IInStockDao extends IGenericDao<InStock, Serializable>{

    List<InStock> getInStockByMateriel(Integer materielId);
}
