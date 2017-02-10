package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomPicDao;
import com.wittymonkey.entity.RoomPic;

@Repository(value="roomPicDao")
public class RoomPicDaoImpl extends GenericDaoImpl<RoomPic> implements IRoomPicDao{

}
