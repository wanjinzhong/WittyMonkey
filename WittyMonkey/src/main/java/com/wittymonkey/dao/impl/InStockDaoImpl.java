package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.entity.InStock;

@Repository(value="inStockDao")
public class InStockDaoImpl extends GenericDaoImpl<InStock> implements IInStockDao{

}
