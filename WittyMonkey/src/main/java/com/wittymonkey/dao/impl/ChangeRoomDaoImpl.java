package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IChangeRoomDao;
import com.wittymonkey.entity.ChangeRoom;

@Repository(value="changeRoomDao")
public class ChangeRoomDaoImpl extends GenericDaoImpl<ChangeRoom> implements IChangeRoomDao{

}
