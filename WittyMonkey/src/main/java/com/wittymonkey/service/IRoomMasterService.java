package com.wittymonkey.service;

import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.RoomMaster;

import java.util.List;

public interface IRoomMasterService {

    RoomMaster getRoomMasterByNo(Hotel hotel, String roomNo);

    void saveRoom(RoomMaster roomMaster);

    List<RoomMaster> getRoomByHotel(Integer hotelId, Integer start, Integer total);

    Integer getTotalByHotel(Integer hotelId);

    Integer getTotalByCondition(Integer type, Object content);

    List<RoomMaster> getRoomByCondition(Integer type, Object content, Integer first, Integer total);

    List<RoomMaster> getRoomByCondition(Integer type, Object content);
}
