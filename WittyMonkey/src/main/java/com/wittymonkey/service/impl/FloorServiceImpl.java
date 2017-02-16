package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFloorDao;
import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.Floor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IFloorService;

import java.sql.SQLException;
import java.util.List;

@Service(value="floorService")
public class FloorServiceImpl implements IFloorService{

    @Autowired
    private IFloorDao floorDao;

    @Autowired
    private IRoomMasterDao roomMasterDao;

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

    @Override
    public void updateFloor(Floor floor) throws SQLException {
        floorDao.update(floor);
    }

    @Override
    public void deleteFloor(Floor floor) throws SQLException {
        // 移动房间至未知
        roomMasterDao.updateFloorToNull(floor.getId());
        // 删除楼层
        floorDao.delete(floor);
    }

    @Override
    public List<Floor> getFloorByHotel(Integer hotelId, Integer start, Integer total) {
        return floorDao.getFloorByPage(hotelId,start,total);
    }

    @Override
    public Integer getTotal(Integer hotelId) {
        return floorDao.getTotal(hotelId);
    }
}
