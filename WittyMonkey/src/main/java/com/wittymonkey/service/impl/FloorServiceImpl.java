package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFloorDao;
import com.wittymonkey.entity.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IFloorService;

@Service(value="floorService")
public class FloorServiceImpl implements IFloorService{

    @Autowired
    private IFloorDao floorDao;

    @Override
    public Floor getFloorByNo(Integer hotelId, Integer floorNo) {
        return floorDao.getFloorByNo(hotelId,floorNo);
    }

    @Override
    public Boolean isFloorExist(Integer hotelId, Integer floorNo) {
        return floorDao.getFloorByNo(hotelId, floorNo) != null;
    }

    @Override
    public void saveFloor(Floor floor) {
        floorDao.save(floor);
    }
}
