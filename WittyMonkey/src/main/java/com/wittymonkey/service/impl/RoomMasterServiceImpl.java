package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.RoomMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IRoomMasterService;

import java.sql.SQLException;
import java.util.List;

@Service(value="roomMasterService")
public class RoomMasterServiceImpl implements IRoomMasterService{

    @Autowired
    private IRoomMasterDao roomMasterDao;

    @Override
    public RoomMaster getRoomById(Integer id) {
        return roomMasterDao.getRoomById(id);
    }

    @Override
    public RoomMaster getRoomMasterByNo(Hotel hotel, String roomNo) {
        return roomMasterDao.getRoomMasterByNo(hotel.getId(),roomNo);
    }

    @Override
    public void saveRoom(RoomMaster roomMaster) {
        roomMasterDao.save(roomMaster);
    }

    @Override
    public void updateRoom(RoomMaster roomMaster) throws SQLException {
        roomMasterDao.update(roomMaster);
    }

    @Override
    public void deleteRoom(RoomMaster roomMaster) throws SQLException {
        roomMasterDao.delete(roomMaster);
    }

    @Override
    public List<RoomMaster> getRoomByHotel(Integer hotelId, Integer start, Integer total) {
        return roomMasterDao.getRoomByHotel(hotelId,start,total);
    }

    @Override
    public Integer getTotalByHotel(Integer hotelId) {
        return roomMasterDao.getTotalByHotel(hotelId);
    }

    @Override
    public Integer getTotalByCondition(Integer type, Object content) {
        return roomMasterDao.getTotalByCondition(type,content);
    }

    @Override
    public List<RoomMaster> getRoomByCondition(Integer type, Object content) {
        return roomMasterDao.getRoomByCondition(type,content, null, null);
    }

    @Override
    public List<RoomMaster> getRoomByCondition(Integer type, Object content, Integer first, Integer total) {
        return roomMasterDao.getRoomByCondition(type,content, first,total);
    }
}
