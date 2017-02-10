package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;

@Repository(value="checkinDao")
public class CheckinDaoImpl extends GenericDaoImpl<Checkin> implements ICheckinDao{

}
