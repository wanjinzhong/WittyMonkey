package com.wittymonkey.service;

import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.RoomMaster;

import java.sql.SQLException;
import java.util.List;

public interface IRoomMasterService {

    RoomMaster getRoomById(Integer id);

    RoomMaster getRoomMasterByNo(Hotel hotel, String roomNo);

    void saveRoom(RoomMaster roomMaster);

    void updateRoom(RoomMaster roomMaster) throws SQLException;

    void deleteRoom(RoomMaster roomMaster) throws SQLException;

    List<RoomMaster> getRoomByHotel(Integer hotelId, Integer start, Integer total);

    Integer getTotalByHotel(Integer hotelId);

    Integer getTotalByCondition(Integer type, Object content);

    List<RoomMaster> getRoomByCondition(Integer type, Object content, Integer first, Integer total);

    List<RoomMaster> getRoomByCondition(Integer type, Object content);
}
