package com.wittymonkey.util;

import com.wittymonkey.controller.IndexController;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IReserveService;
import com.wittymonkey.service.IRoomMasterService;
import com.wittymonkey.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.object.SimpleRecordOperation;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by neilw on 2017/2/16.
 */
public class ChangeToSimple {

    public static List<SimpleFloor> floorList(List<Floor> floors) {
        List<SimpleFloor> simpleFloors = new ArrayList<SimpleFloor>();
        for (Floor floor : floors) {
            SimpleFloor simpleFloor = new SimpleFloor();
            simpleFloor.setId(floor.getId());
            simpleFloor.setFloorNo(floor.getFloorNo());
            Iterator<RoomMaster> it = floor.getRoomMasters().iterator();
            while (it.hasNext()) {
                if (it.next().getDelete()) {
                    it.remove();
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

    public static List<SimpleRoom> roomList(List<RoomMaster> roomMasters) {
        List<SimpleRoom> simpleRooms = new ArrayList<SimpleRoom>();
        for (RoomMaster room : roomMasters) {
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

    public static List<SimpleReserve> reserveList(List<Reserve> reserves) {
        List<SimpleReserve> simpleReserves = new ArrayList<SimpleReserve>();
        for (Reserve reserve : reserves) {
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

    public static List<SimpleMaterielType> materielTypeList(List<MaterielType> materielTypes) {
        List<SimpleMaterielType> simpleMaterielTypes = new ArrayList<SimpleMaterielType>();
        for (MaterielType materielType : materielTypes) {
            SimpleMaterielType simpleMaterielType = new SimpleMaterielType();
            simpleMaterielType.setId(materielType.getId());
            simpleMaterielType.setName(materielType.getName());
            simpleMaterielType.setNote(materielType.getNote());
            simpleMaterielType.setEntryUser(materielType.getEntryUser().getRealName());
            simpleMaterielType.setEntryDatetime(materielType.getEntryDatetime());
            simpleMaterielType.setMaterielNum(materielType.getMateriels().size());
            simpleMaterielTypes.add(simpleMaterielType);
        }
        return simpleMaterielTypes;
    }

    public static List<SimpleUser> userList(List<User> users) {
        List<SimpleUser> simpleUsers = new ArrayList<SimpleUser>();
        for (User user : users) {
            SimpleUser simpleUser = new SimpleUser();
            simpleUser.setId(user.getId());
            simpleUser.setRealName(user.getRealName());
            simpleUser.setStaffNo(user.getStaffNo());
            simpleUser.setIdCardNo(user.getIdCardNo());
            simpleUser.setHotelName(user.getHotel().getName());
            simpleUser.setEntryDatetime(user.getEntryDatetime());
            simpleUser.setEntryUser(user.getEntryUser().getRealName());
            for (Role role : user.getRoles()) {
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

    public static List<SimpleRole> roleList(String lang, List<Role> roles) {
        List<SimpleRole> simpleRoles = new ArrayList<SimpleRole>();
        Properties props = new Properties();
        try {
            props.load(IndexController.class.getResourceAsStream("/i18n/messages_" + lang + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Role role : roles) {
            SimpleRole simpleRole = new SimpleRole();
            simpleRole.setId(role.getId());
            simpleRole.setEntryDatetime(role.getEntryDatetime());
            simpleRole.setEntryUser(role.getEntryUser().getRealName());
            simpleRole.setName(role.getName());
            simpleRole.setNote(role.getNote());
            for (User user : role.getUsers()) {
                simpleRole.getUsers().add(user.getRealName());
            }
            for (Menu menu : role.getMenus()) {
                String menuName = null;
                switch (menu.getId()) {
                    case 1:
                        menuName = props.getProperty("index.menu.floor");
                        break;
                    case 2:
                        menuName = props.getProperty("index.menu.room");
                        break;
                    case 3:
                        menuName = props.getProperty("index.menu.materiel");
                        break;
                    case 4:
                        menuName = props.getProperty("index.menu.inventory");
                        break;
                    case 5:
                        menuName = props.getProperty("index.menu.role");
                        break;
                    case 6:
                        menuName = props.getProperty("index.menu.room");
                        break;
                    case 7:
                        menuName = props.getProperty("index.menu.leave");
                        break;
                    case 8:
                        menuName = props.getProperty("index.menu.finance");
                        break;
                    case 9:
                        menuName = props.getProperty("index.menu.report");
                        break;
                    case 10:
                        menuName = props.getProperty("index.menu.room");
                        break;
                    case 11:
                        menuName = props.getProperty("index.menu.notify");
                        break;
                    case 12:
                        menuName = props.getProperty("index.menu.room.settting");
                        break;
                }
                simpleRole.getMenus().add(menuName);
            }
            simpleRoles.add(simpleRole);
        }
        return simpleRoles;
    }
}
