package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.RoomMaster;

public interface IRoomMasterDao extends IGenericDao<RoomMaster, Serializable>{


    void moveRoomToFloor(Integer fromFloorNo, Integer toFloorNo);

    /**
     * 删除楼层时将该楼层下的所有房间的楼层号置空
     * @param fromFloorId
     */
    void updateFloorToNull(Integer fromFloorId);

    RoomMaster getRoomMasterByNo(Integer hotelId, String roomNo);

    List<RoomMaster> getRoomByHotel(Integer hotelId, Integer first, Integer total);

    Integer getTotalByHotel(Integer hotelId);
}
