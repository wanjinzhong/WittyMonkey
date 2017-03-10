package com.wittymonkey.dao.impl;

import com.wittymonkey.controller.RoomController;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.RoomMaster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "roomMasterDao")
public class RoomMasterDaoImpl extends GenericDaoImpl<RoomMaster> implements IRoomMasterDao {

    private SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    @Override
    public RoomMaster getRoomById(Integer id) {
        String hql = "from RoomMaster where id = ?";
        return queryOneHql(hql, id);
    }

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
        String hql = "from RoomMaster where floor.hotel.id = :hotelId and number = :roomNo and isDelete = false";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("roomNo", roomNo);
        return queryOneHql(hql, param);
    }

    @Override
    public List<RoomMaster> getRoomByHotel(Integer hotelId, Integer first, Integer total) {
        String hql = "from RoomMaster where floor.hotel.id = :hotelId and isDelete = false order by number";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return queryListHQL(hql, param, first, total);
    }

    @Override
    public Integer getTotalByHotel(Integer hotelId) {
        String hql = "select count(1) from RoomMaster where floor.hotel.id = :hotelId and isDelete = false";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return countHQL(hql, param);
    }

    @Override
    public Integer getTotalByCondition(Integer type, Object content) {
        String hql = "select count(1) from RoomMaster where isDelete = false and ";
        Integer result = 0;
        if (type == RoomController.TYPE_STATUS) {
            hql += "status = ?";
            result = countHQL(hql, Integer.parseInt(String.valueOf(content)));
        } else if (type == RoomController.TYPE_FLOOR_ID) {
            hql += "floor.id = ?";
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
    public List<RoomMaster> getRoomByCondition(Integer hotelId, Integer type, Object content, Integer first, Integer total) {
        String hql = "from RoomMaster where isDelete = false and floor.hotel.id = :hotelId and ";
        List<RoomMaster> result = null;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        if (type == RoomController.TYPE_STATUS) {
            hql += "status = :status";
            param.put("status", Integer.parseInt(String.valueOf(content)));
        } else if (type == RoomController.TYPE_FLOOR_ID) {
            hql += "floor.id = :floorId";
            param.put("floorId",  Integer.parseInt(String.valueOf(content)));
        } else if (type == RoomController.TYPE_ROOM_NO) {
            hql += "number = :number";
            param.put("number", String.valueOf(content));
        } else if (type == RoomController.TYPE_ROOM_NAME) {
            hql += "name = :name";
            param.put("name", String.valueOf(content));
        } else if (type == RoomController.TYPE_PERSON_NUM) {
            hql += "availableNum >= :num";
            param.put("num",  Integer.parseInt(String.valueOf(content)));
        }
        return queryListHQL(hql, param, first, total);
    }

    @Override
    public List<RoomMaster> getFreeAndReservedByDate(Integer hotel, Integer status, Date from, Date to) {
        String hql = "from RoomMaster where floor.hotel.id = :hotelId and status = :status and isDelete = false order by number";
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("hotelId", hotel);
        map.put("status", status);
        return queryListHQL(hql, map);
    }
}
