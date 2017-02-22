package com.wittymonkey.dao.impl;

import com.wittymonkey.controller.RoomController;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.RoomMaster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "roomMasterDao")
public class RoomMasterDaoImpl extends GenericDaoImpl<RoomMaster> implements IRoomMasterDao {

    @Override
    public void moveRoomToFloor(Integer fromFloorId, Integer toFloorId) {
        //String hql = "update RoomMaster set floor.id = :toId where floor.id = fromId";
        String sql = "update room_master set floor_id = ?1 where floor_id = ?2";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", toFloorId);
        map.put("2", fromFloorId);
        executeSQL(sql, map);
    }

    @Override
    public void updateFloorToNull(Integer fromFloorId) {
        String sql = "update room_master set floor_id = null where floor_id = ?1";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", fromFloorId);
        executeSQL(sql, map);
    }

    @Override
    public RoomMaster getRoomMasterByNo(Integer hotelId, String roomNo) {
        String hql = "from RoomMaster where floor.hotel.id = :hotelId and number = :roomNo";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("roomNo", roomNo);
        return queryOneHql(hql, param);
    }

    @Override
    public List<RoomMaster> getRoomByHotel(Integer hotelId, Integer first, Integer total) {
        String hql = "from RoomMaster where floor.hotel.id = :hotelId order by number";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return queryListHQL(hql, param, first, total);
    }

    @Override
    public Integer getTotalByHotel(Integer hotelId) {
        String hql = "select count(1) from RoomMaster where floor.hotel.id = :hotelId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return countHQL(hql, param);
    }

    @Override
    public Integer getTotalByCondition(Integer type, Object content) {
        String hql = "select count(1) from RoomMaster where ";
        Integer result = 0;
        if (type == RoomController.TYPE_STATUS) {
            hql += "status = ?";
            result = countHQL(hql, Integer.parseInt(String.valueOf(content)));
        } else if (type == RoomController.TYPE_FLOOR_NO) {
            hql += "floor.floorNo = ?";
            result = countHQL(hql, Integer.parseInt(String.valueOf(content)));
        } else if (type == RoomController.TYPE_ROOM_NO) {
            hql += "number = ?";
            result = countHQL(hql, String.valueOf(content));
        } else if (type == RoomController.TYPE_ROOM_NAME) {
            hql += "name = ?";
            result = countHQL(hql, String.valueOf(content));
        } else if (type == RoomController.TYPE_PERSON_NUM) {
            hql += "availableNum >= ?";
            result = countHQL(hql, Integer.parseInt(String.valueOf(content)));
        }
        return result;
    }

    @Override
    public List<RoomMaster> getRoomByCondition(Integer type, Object content, Integer first, Integer total) {
        String hql = "from RoomMaster where ";
        List<RoomMaster> result = null;
        if (type == RoomController.TYPE_STATUS) {
            hql += "status = ?";
            result = queryListHQL(hql, Integer.parseInt(String.valueOf(content)), first, total);
        } else if (type == RoomController.TYPE_FLOOR_NO) {
            hql += "floor.floorNo = ?";
            result = queryListHQL(hql, Integer.parseInt(String.valueOf(content)), first, total);
        } else if (type == RoomController.TYPE_ROOM_NO) {
            hql += "number = ?";
            result = queryListHQL(hql, String.valueOf(content), first, total);
        } else if (type == RoomController.TYPE_ROOM_NAME) {
            hql += "name = ?";
            result = queryListHQL(hql, String.valueOf(content), first, total);
        } else if (type == RoomController.TYPE_PERSON_NUM) {
            hql += "availableNum >= ?";
            result = queryListHQL(hql, Integer.parseInt(String.valueOf(content)), first, total);
        }
        return result;
    }
}
