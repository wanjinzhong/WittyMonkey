package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFloorDao;
import com.wittymonkey.entity.Floor;

@Repository(value="floorDao")
public class FloorDaoImpl extends GenericDaoImpl<Floor> implements IFloorDao{

}
