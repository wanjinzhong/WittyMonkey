package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.RoomMaster;

@Repository(value="roomMasterDao")
public class RoomMasterDaoImpl extends GenericDaoImpl<RoomMaster> implements IRoomMasterDao{

}
