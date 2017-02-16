package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoomMasterDao;
import com.wittymonkey.entity.RoomMaster;

import java.util.HashMap;
import java.util.Map;

@Repository(value="roomMasterDao")
public class RoomMasterDaoImpl extends GenericDaoImpl<RoomMaster> implements IRoomMasterDao{

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
    public void updateFloorToNull(Integer fromFloorId){
        String sql = "update room_master set floor_id = null where floor_id = ?1";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", fromFloorId);
        executeSQL(sql, map);
    }
}
