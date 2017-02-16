package com.wittymonkey.util;

import com.wittymonkey.entity.Floor;
import com.wittymonkey.vo.SimpleFloor;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neilw on 2017/2/16.
 */
public class ChangeToSimple {
    public static List<SimpleFloor> floorList(List<Floor> floors){
        List<SimpleFloor> simpleFloors = new ArrayList<SimpleFloor>();
        for (Floor floor : floors) {
            SimpleFloor simpleFloor = new SimpleFloor();
            simpleFloor.setId(floor.getId());
            simpleFloor.setFloorNo(floor.getFloorNo());
            simpleFloor.setRoomNum(floor.getRoomMasters().size());
            simpleFloor.setEntryUser(floor.getEntryUser().getRealName());
            simpleFloor.setEntryDatetime(floor.getEntryDatetime());
            simpleFloor.setNote(floor.getNote());
            simpleFloors.add(simpleFloor);
        }
        return simpleFloors;
    }
}
