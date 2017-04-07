package com.wittymonkey.util;

import com.wittymonkey.entity.*;
import com.wittymonkey.service.IReserveService;
import com.wittymonkey.service.IRoomMasterService;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleReserve;
import com.wittymonkey.vo.SimpleRoom;
import com.wittymonkey.vo.SimpleUser;
import org.springframework.beans.factory.annotation.Autowired;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
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
            for (int i = 0; i < floor.getRoomMasters().size(); i ++){
                if (floor.getRoomMasters().get(i).getDelete() == true){
                    floor.getRoomMasters().remove(i);
                }
            }
            simpleFloor.setRoomNum(floor.getRoomMasters().size());
            simpleFloor.setEntryUser(floor.getEntryUser().getRealName());
            simpleFloor.setEntryDatetime(floor.getEntryDatetime());
            simpleFloor.setNote(floor.getNote());
            simpleFloors.add(simpleFloor);
        }
        return simpleFloors;
    }

    public static List<SimpleRoom> roomList(List<RoomMaster> roomMasters){
        List<SimpleRoom> simpleRooms = new ArrayList<SimpleRoom>();
        for (RoomMaster room : roomMasters){
            SimpleRoom simpleRoom = new SimpleRoom();
            simpleRoom.setId(room.getId());
            simpleRoom.setFloorNo(room.getFloor().getFloorNo());
            simpleRoom.setArea(room.getArea());
            simpleRoom.setName(room.getName());
            simpleRoom.setNumber(room.getNumber());
            simpleRoom.setPrice(room.getPrice());
            simpleRoom.setSingleBedNum(room.getSingleBedNum());
            simpleRoom.setDoubleBedNum(room.getDoubleBedNum());
            simpleRoom.setAvailableNum(room.getAvailableNum());
            simpleRoom.setStatus(room.getStatus());
            simpleRoom.setThumbUrl(room.getThumbUrl());
            simpleRoom.setEntryDatetime(room.getEntryDatetime());
            simpleRoom.setUserName(room.getEntryUser().getRealName());
            simpleRooms.add(simpleRoom);
        }
        return simpleRooms;
    }

    public static List<SimpleReserve> reserveList(List<Reserve> reserves){
        List<SimpleReserve> simpleReserves = new ArrayList<SimpleReserve>();
        for (Reserve reserve : reserves){
            SimpleReserve simpleReserve = new SimpleReserve();
            simpleReserve.setId(reserve.getId());
            simpleReserve.setCustomer(reserve.getCustomer());
            simpleReserve.setReserveDate(reserve.getReserveDate());
            simpleReserve.setEstCheckinDate(reserve.getEstCheckinDate());
            simpleReserve.setEstCheckoutDate(reserve.getEstCheckoutDate());
            simpleReserve.setDeposit(reserve.getDeposit());
            simpleReserve.setStatus(reserve.getStatus());
            simpleReserve.setEntryDatetime(reserve.getEntryDatetime());
            simpleReserve.setEntryUser(reserve.getEntryUser().getRealName());
            simpleReserve.setNote(reserve.getNote());
            simpleReserves.add(simpleReserve);
        }
        return simpleReserves;
    }

    public static List<SimpleUser> userList(List<User> users){
        List<SimpleUser> simpleUsers = new ArrayList<SimpleUser>();
        for (User user : users){
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setId(user.getId());
            simpleUser.setRealName(user.getRealName());
            simpleUser.setLoginName(user.getLoginName());
            simpleUser.setIdCardNo(user.getIdCardNo());
            simpleUser.setHotelName(user.getHotel().getName());
            simpleUser.setEntryDatetime(user.getEntryDatetime());
            simpleUser.setEntryUser(user.getEntryUser().getRealName());
            for(Role role : user.getRoles()){
                simpleUser.getRoles().add(role.getName());
            }
            simpleUser.setTel(user.getTel());
            simpleUser.setEmail(user.getEmail());
            simpleUser.setRegistDate(user.getRegistDate());
            simpleUser.setDimissionDate(user.getDimissionDate());
            simpleUser.setDimissionNote(user.getDimissionNote());
            simpleUsers.add(simpleUser);
        }
        return simpleUsers;
    }
}
