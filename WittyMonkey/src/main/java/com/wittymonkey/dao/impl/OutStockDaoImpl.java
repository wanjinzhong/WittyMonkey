package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IOutStockDao;
import com.wittymonkey.entity.OutStock;

@Repository(value="outStockDao")
public class OutStockDaoImpl extends GenericDaoImpl<OutStock> implements IOutStockDao{

}
