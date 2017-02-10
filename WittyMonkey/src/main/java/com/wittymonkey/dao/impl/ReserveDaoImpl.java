package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;

@Repository(value="reserveDao")
public class ReserveDaoImpl extends GenericDaoImpl<Reserve> implements IReserveDao{

}
