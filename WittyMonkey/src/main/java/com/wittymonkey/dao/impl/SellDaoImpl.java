package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISellDao;
import com.wittymonkey.entity.Sell;

@Repository(value="sellDao")
public class SellDaoImpl extends GenericDaoImpl<Sell> implements ISellDao{

}
