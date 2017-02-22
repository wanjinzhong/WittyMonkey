package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.IFloorService;
import com.wittymonkey.service.IRoomMasterService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Page;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleRoom;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by neilw on 2017/2/20.
 */
@Controller
public class RoomController {

    public static final Integer TYPE_STATUS = 0;
    public static final Integer TYPE_FLOOR_NO = 1;
    public static final Integer TYPE_ROOM_NO = 2;
    public static final Integer TYPE_ROOM_NAME = 3;
    public static final Integer TYPE_PERSON_NUM = 4;

    @Autowired
    private IRoomMasterService roomMasterService;

    @Autowired
    private IFloorService floorService;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "toAddRoom", method = RequestMethod.GET)
    public String toAddRoom(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        List<Floor> floors = loginUser.getHotel().getFloors();
        Collections.sort(floors, new Comparator<Floor>() {
            @Override
            public int compare(Floor o1, Floor o2) {
                return o1.getFloorNo() - o2.getFloorNo();
            }
        });
        List<SimpleFloor> simpleFloors = ChangeToSimple.floorList(floors);
        request.getSession().setAttribute("floors", simpleFloors);
        return "room_add";
    }

    @RequestMapping(value = "validateRoomNo", method = RequestMethod.GET)
    @ResponseBody
    public String validateRoomNo(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = loginUser.getHotel();
        JSONObject json = new JSONObject();
        String roomNo = request.getParameter("roomNo");
        Integer res = validateRoomNo(hotel, roomNo);
        json.put("status", res);
        return json.toJSONString();
    }

    /**
     * 保存房间
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>房间号已存在</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>房间号为空</td>
     * </tr>
     * <tr>
     * <td>402</td>
     * <td>房间号过长</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>房间名过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>单人床不正确</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>双人床不正确</td>
     * </tr>
     * <tr>
     * <td>440</td>
     * <td>可住人数不正确</td>
     * </tr>
     * <tr>
     * <td>440</td>
     * <td>可住人数不正确</td>
     * </tr>
     * <tr>
     * <td>450</td>
     * <td>楼层号不存在</td>
     * </tr>
     * <tr>
     * <td>460</td>
     * <td>面积不正确</td>
     * </tr>
     * <tr>
     * <td>470</td>
     * <td>价格不正确</td>
     * </tr>
     * <tr>
     * <td>480</td>
     * <td>其它设施过长</td>
     * </tr>
     * <tr>
     * <td>490</td>
     * <td>备注过长</td>
     * </tr>
     */
    @RequestMapping(value = "addRoom", method = RequestMethod.GET)
    @ResponseBody
    public String addRoom(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        JSONObject json = new JSONObject();
        String roomNo = request.getParameter("roomNum");
        String roomName = request.getParameter("roomName");
        String singleBed = request.getParameter("singleBedNum");
        String doubleBed = request.getParameter("doubleBedNum");
        String available = request.getParameter("availableNum");
        String roomArea = request.getParameter("area");
        String roomPrice = request.getParameter("price");
        String floorId = request.getParameter("floorId");
        String[] extra = request.getParameterValues("extra");
        String other = request.getParameter("otherFacility");
        String note = request.getParameter("note");
        Integer singleBedNum;
        Integer doubleBedNum;
        Integer availableNum;
        Double area;
        Double price;
        switch (validateRoomNo(loginUser.getHotel(), roomNo)) {
            case 200:
                json.put("status", 400);
                return json.toJSONString();
            case 400:
                json.put("status", 401);
                return json.toJSONString();
            case 401:
                json.put("status", 402);
                return json.toJSONString();
        }
        if (roomName.length() > 20) {
            json.put("status", 410);
            return json.toJSONString();
        }
        try {
            singleBedNum = Integer.parseInt(singleBed);
            if (singleBedNum < 0 || singleBedNum > 10) {
                json.put("status", 420);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 420);
            return json.toJSONString();
        }
        try {
            doubleBedNum = Integer.parseInt(doubleBed);
            if (doubleBedNum < 0 || doubleBedNum > 10) {
                json.put("status", 430);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 430);
            return json.toJSONString();
        }
        try {
            availableNum = Integer.parseInt(available);
            if (availableNum < 0 || availableNum > 100) {
                json.put("status", 440);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 440);
            return json.toJSONString();
        }
        Floor floor;
        try {
            floor = floorService.getFloorById(Integer.parseInt(floorId));
            if (floor == null) {
                json.put("status", 450);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 450);
            return json.toJSONString();
        }
        try {
            area = Double.parseDouble(roomArea);
        } catch (NumberFormatException e) {
            json.put("status", 460);
            return json.toJSONString();
        }
        try {
            price = Double.parseDouble(roomPrice);
        } catch (NumberFormatException e) {
            json.put("status", 470);
            return json.toJSONString();
        }
        if (other.length() > 1024) {
            json.put("status", 480);
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 490);
            return json.toJSONString();
        }


        RoomExt roomExt = new RoomExt();
        List<String> extraInfo = Arrays.asList(extra);
        if (extraInfo.contains("window")) {
            roomExt.setHasWindow(true);
        } else {
            roomExt.setHasWindow(false);
        }
        if (extraInfo.contains("wifi")) {
            roomExt.setHasWifi(true);
        } else {
            roomExt.setHasWifi(false);
        }
        if (extraInfo.contains("reticel")) {
            roomExt.setHasReticle(true);
        } else {
            roomExt.setHasReticle(false);
        }
        if (extraInfo.contains("tv")) {
            roomExt.setHasTV(true);
        } else {
            roomExt.setHasTV(false);
        }
        if (extraInfo.contains("phone")) {
            roomExt.setHasPhone(true);
        } else {
            roomExt.setHasPhone(false);
        }
        if (extraInfo.contains("kettle")) {
            roomExt.setHasKettle(true);
        } else {
            roomExt.setHasKettle(false);
        }
        if (extraInfo.contains("extraBed")) {
            roomExt.setExtraBed(true);
        } else {
            roomExt.setExtraBed(false);
        }
        if (extraInfo.contains("aircondition")) {
            roomExt.setHasAirCondition(true);
        } else {
            roomExt.setHasAirCondition(false);
        }
        roomExt.setOtherFacility(other);
        roomExt.setNote(note);

        RoomMaster roomMaster = new RoomMaster();
        roomMaster.setArea(area);
        roomMaster.setAvailableNum(availableNum);
        roomMaster.setSingleBedNum(singleBedNum);
        roomMaster.setDoubleBedNum(doubleBedNum);
        roomMaster.setFloor(floor);
        roomMaster.setName(roomName);
        roomMaster.setNumber(roomNo);
        roomMaster.setPrice(price);
        roomMaster.setStatus(0);
        roomMaster.setEntryDatetime(new Date());
        roomMaster.setEntryUser(userService.getUserById(loginUser.getId()));
        roomMaster.setRoomExt(roomExt);
        roomMasterService.saveRoom(roomMaster);
        json.put("status", 200);
        return json.toJSONString();
    }

    @RequestMapping(value = "getRoomByHotel", method = RequestMethod.GET)
    @ResponseBody
    public String getRoomByHotel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrPage(curr);
        Integer count = roomMasterService.getTotalByHotel(hotel.getId());
        List<RoomMaster> roomMasters = roomMasterService.getRoomByHotel(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleRoom> simpleRooms = ChangeToSimple.roomList(roomMasters);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRooms);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "getRoomByCondition", method = RequestMethod.GET)
    @ResponseBody
    public String getRoomByCondition(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        Object content = request.getParameter("content");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Page page = new Page();
        page.setPageSize(pageSize);
        page.setCurrPage(curr);
        Integer count = roomMasterService.getTotalByCondition(type, content);
        List<RoomMaster> roomMasters = roomMasterService.getRoomByCondition(type,content,(curr - 1) * pageSize, pageSize);
        List<SimpleRoom> simpleRooms = ChangeToSimple.roomList(roomMasters);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRooms);
        json.put("data", array);
        return json.toJSONString();
    }

    /**
     * 根据验证房间号是否存在
     *
     * @param hotel
     * @param roomNo
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>房间号存在</td>
     * </tr>
     * <tr>
     * <td>210</td>
     * <td>房间号不存在</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有输入房间号</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>房间号过长</td>
     * </tr>
     */
    public Integer validateRoomNo(Hotel hotel, String roomNo) {
        if (roomNo == null || roomNo.length() <= 0) {
            return 400;
        } else if (roomNo.length() > 10) {
            return 401;
        } else {
            RoomMaster room = roomMasterService.getRoomMasterByNo(hotel, roomNo);
            if (room != null) {
                return 200;
            } else {
                return 210;
            }
        }
    }
}
