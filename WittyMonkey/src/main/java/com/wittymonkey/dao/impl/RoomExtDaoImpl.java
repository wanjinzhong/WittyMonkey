package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomExtDao;
import com.wittymonkey.entity.RoomExt;

@Repository(value="roomExtDao")
public class RoomExtDaoImpl extends GenericDaoImpl<RoomExt> implements IRoomExtDao{

}
