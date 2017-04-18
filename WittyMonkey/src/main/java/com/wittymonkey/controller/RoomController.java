package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.*;
import com.wittymonkey.service.*;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.util.IDCardValidate;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import com.wittymonkey.vo.SimpleReserve;
import com.wittymonkey.vo.SimpleRoom;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by neilw on 2017/2/20.
 */
@Controller
public class RoomController {

    public static final Integer TYPE_STATUS = 0;
    public static final Integer TYPE_FLOOR_ID = 1;
    public static final Integer TYPE_ROOM_NO = 2;
    public static final Integer TYPE_ROOM_NAME = 3 ;
    public static final Integer TYPE_PERSON_NUM = 4;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private RoomMaster chooseRoom = null;

    @Autowired
    private IRoomMasterService roomMasterService;

    @Autowired
    private IFloorService floorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IReserveService reserveService;

    @Autowired
    private ICheckinService checkinService;

    @Autowired
    private IChangeRoomService changeRoomService;

    @RequestMapping(value = "toAddRoom", method = GET)
    public String toAddRoom(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        List<Floor> floors = floorService.getFloorByHotel(loginUser.getHotel().getId(), null, null);
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


    @RequestMapping(value = "showRoomDetail", method = GET)
    public String showRoomDetail(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("roomId"));
        RoomMaster roomMaster = roomMasterService.getRoomById(id);
        request.getSession().setAttribute("room", roomMaster);

        return "room_detail";
    }

    @RequestMapping(value = "toReserve", method = GET)
    public String toReserve(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        RoomMaster roomMaster = roomMasterService.getRoomById(id);
        request.getSession().setAttribute("reserveRoom", roomMaster);
        return "reserve";
    }

    @RequestMapping(value = "toChooseRoom", method = GET)
    public String toChooseRoom(HttpServletRequest request) {
        Integer checkinId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("checkinId", checkinId);
        return "choose_room";
    }

    @RequestMapping(value = "getFreeRoomByDateRange", method = GET)
    @ResponseBody
    public String getFreeRoomByDateRange(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer checkinId = Integer.parseInt(request.getParameter("id"));
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Checkin checkin = checkinService.getCheckinById(checkinId);
        Integer count = roomMasterService.getTotalFreeByDate(hotel.getId(), RoomMaster.FREE, new Date(), checkin.getEstCheckoutDate());
        List<RoomMaster> rooms = roomMasterService.getFreeByDate(hotel.getId(), RoomMaster.FREE, new Date(), checkin.getEstCheckoutDate(), (curr - 1) * pageSize, pageSize);
        request.getSession().setAttribute("rooms", rooms);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(ChangeToSimple.roomList(rooms));
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "toCheckin", method = GET)
    public String toCheckin(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        RoomMaster roomMaster = roomMasterService.getRoomById(id);
        request.getSession().setAttribute("checkinRoom", roomMaster);
        request.getSession().removeAttribute("reserve");
        request.getSession().removeAttribute("fromDate");
        request.getSession().removeAttribute("toDate");
        // 根据今天获取是否有预定
        Reserve reserve = reserveService.getReserveByDate(roomMaster.getId(), Reserve.RESERVED, new Date());
        if (reserve != null) {
            String fromDate = sdf.format(reserve.getEstCheckinDate());
            String toDate = sdf.format(reserve.getEstCheckoutDate());
            request.getSession().setAttribute("reserve", reserve);
            request.getSession().setAttribute("fromDate", fromDate);
            request.getSession().setAttribute("toDate", toDate);
        }
        return "checkin";
    }

    @RequestMapping(value = "toChange", method = GET)
    public String toChange(HttpServletRequest request) {
        Integer roomId = Integer.parseInt(request.getParameter("id"));
        Checkin checkin = checkinService.getCheckinByRoomUncomplete(roomId);
        Integer totalDays = DateUtil.dateDiffDays(new Date(), checkin.getEstCheckoutDate());
        request.setAttribute("checkin", checkin);
        request.setAttribute("now", sdf.format(new Date()));
        request.setAttribute("to", sdf.format(checkin.getEstCheckoutDate()));
        request.setAttribute("total", totalDays);
        if (chooseRoom != null) {
            request.setAttribute("chooseRoom", chooseRoom);
            Double diff = (chooseRoom.getPrice() - checkin.getRoom().getPrice()) * totalDays;
            request.setAttribute("diff", diff);
        }
        return "change_room";
    }

    @RequestMapping(value = "toCheckout", method = GET)
    public String toCheckout(HttpServletRequest request) {
        String id = request.getParameter("id");
        Integer roomId = null;
        try {
            roomId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Checkin checkin = checkinService.getCheckinByRoomUncomplete(roomId);
        request.getSession().setAttribute("checkin", checkin);
        request.getSession().setAttribute("checkinDate", sdf.format(checkin.getCheckinDate()));
        request.getSession().setAttribute("estCheckoutDate", sdf.format(checkin.getEstCheckoutDate()));
        return "checkout";
    }

    @RequestMapping(value = "toShowReserve", method = GET)
    public String toShowReserve(HttpServletRequest request) {
        Integer roomId = Integer.parseInt(request.getParameter("id"));
        RoomMaster roomMaster = roomMasterService.getRoomById(roomId);
        request.getSession().setAttribute("room", roomMaster);
        return "show_reserve";
    }

    /**
     * 退房
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>退房成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>入住号不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>房间已退</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     */
    @RequestMapping(value = "checkout", method = GET)
    @ResponseBody
    public String checkout(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer checkinId = null;
        try {
            checkinId = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Checkin checkin = checkinService.getCheckinById(checkinId);
        if (checkin == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (checkin.getActCheckoutDate() != null) {
            json.put("status", 410);
            return json.toJSONString();
        }
        User operator = getLoginUserPersistence(request);
        checkin.setActCheckoutDate(new Date());
        checkin.setEntryUser(operator);
        checkin.setEntryDatetime(new Date());
        checkin.getRoom().setStatus(RoomMaster.CLEAN);
        checkin.getRoom().setEntryUser(operator);
        checkin.getRoom().setEntryDatetime(new Date());
        try {
            checkinService.update(checkin);
            json.put("status", 200);
            return json.toJSONString();
        } catch (SQLException e) {
            json.put("status", 500);
            return json.toJSONString();
        }
    }

    public User getLoginUserPersistence(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loginUser");
        return userService.getUserById(user.getId());
    }

    @RequestMapping(value = "validateRoomNo", method = GET)
    @ResponseBody
    public String validateRoomNo(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = loginUser.getHotel();
        JSONObject json = new JSONObject();
        String roomNo = request.getParameter("roomNo");
        String method = request.getParameter("method");
        Integer res = validateRoomNo(request, method, roomNo);
        json.put("status", res);
        return json.toJSONString();
    }


    @RequestMapping(value = "getReserveByPage", method = GET)
    @ResponseBody
    public String getReserveByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer roomId = Integer.parseInt(request.getParameter("roomId"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Integer count = reserveService.getTotalByRoomIdReserved(roomId, Reserve.RESERVED);
        List<Reserve> reserves = reserveService.getReserveByRoomId(roomId, Reserve.RESERVED, (curr - 1) * pageSize, pageSize);
        List<SimpleReserve> simpleReserves = ChangeToSimple.reserveList(reserves);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleReserves);
        json.put("data", array);
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
    @RequestMapping(value = "saveRoom", method = GET)
    @ResponseBody
    public String saveRoom(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        JSONObject json = new JSONObject();
        String method = request.getParameter("method");
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
        switch (validateRoomNo(request, method, roomNo)) {
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


        RoomMaster roomMaster;
        RoomExt roomExt;
        if (method.equals("add")) {
            roomMaster = new RoomMaster();
            roomExt = new RoomExt();
        } else {
            roomMaster = roomMasterService.getRoomById(((RoomMaster) request.getSession().getAttribute("room")).getId());
            roomExt = roomMaster.getRoomExt();
        }
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
        roomMaster.setDelete(false);
        if (method.equals(Constraint.ADD)) {
            roomMasterService.saveRoom(roomMaster);
        } else if (method.equals(Constraint.UPDATE)) {
            try {
                roomMasterService.updateRoom(roomMaster);
            } catch (SQLException e) {
                e.printStackTrace();
                json.put("status", 500);
                return json.toJSONString();
            }
        }
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 删除房间（伪删除）
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>删除成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>房间不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>房间有人入住</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>房间有人预订</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "deleteRoom", method = GET)
    @ResponseBody
    public String deleteRoom(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer id = Integer.parseInt(request.getParameter("id"));
        RoomMaster roomMaster = roomMasterService.getRoomById(id);
        if (roomMaster == null) {
            json.put("status", 400);
            return json.toJSONString();
        } else if (roomMaster.getStatus() == 1) {
            json.put("status", 410);
            return json.toJSONString();
        } else if (roomMaster.getStatus() == 2) {
            json.put("status", 411);
            return json.toJSONString();
        } else {
            roomMaster.setDelete(true);
            try {
                roomMasterService.updateRoom(roomMaster);
            } catch (SQLException e) {
                json.put("status", 500);
                return json.toJSONString();
            }
            json.put("status", 200);
            return json.toJSONString();
        }
    }

    @RequestMapping(value = "getRoomByHotel", method = GET)
    @ResponseBody
    public String getRoomByHotel(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = roomMasterService.getTotalByHotel(hotel.getId());
        List<RoomMaster> roomMasters = roomMasterService.getRoomByHotel(hotel.getId(), (curr - 1) * pageSize, pageSize);
        List<SimpleRoom> simpleRooms = ChangeToSimple.roomList(roomMasters);
        changeStatus(simpleRooms);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRooms);
        json.put("data", array);
        return json.toJSONString();
    }

    @RequestMapping(value = "getRoomByCondition", method = GET)
    @ResponseBody
    public String getRoomByCondition(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        Object content = request.getParameter("content");
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = roomMasterService.getTotalByCondition(type, content);
        List<RoomMaster> roomMasters = roomMasterService.getRoomByCondition(hotel.getId(), type, content, (curr - 1) * pageSize, pageSize);
        List<SimpleRoom> simpleRooms = ChangeToSimple.roomList(roomMasters);
        changeStatus(simpleRooms);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleRooms);
        json.put("data", array);
        return json.toJSONString();
    }

    /**
     * 预定
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>预定成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>身份证号不正确</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>没有输入名字</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>名字过长</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>没有输入电话</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>电话过长</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>没有输入时间</td>
     * </tr>
     * <tr>
     * <td>431</td>
     * <td>时间不正确</td>
     * </tr>
     * <tr>
     * <td>432</td>
     * <td>开始大于结束</td>
     * </tr>
     * <tr>
     * <td>440</td>
     * <td>没有输入定金</td>
     * </tr>
     * <tr>
     * <td>441</td>
     * <td>定金不正确</td>
     * </tr>
     * <tr>
     * <td>450</td>
     * <td>备注过长</td>
     * </tr>
     * <tr>
     * <td>460</td>
     * <td>时间冲突</td>
     * </tr>
     */
    @RequestMapping(value = "reserve", method = GET)
    @ResponseBody
    public String reserve(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer roomId = Integer.parseInt(request.getParameter("roomId"));
        RoomMaster room = roomMasterService.getRoomById(roomId);
        String cust = request.getParameter("custId");
        Integer custId = null;
        if (cust != null && !cust.equals("")) {
            custId = Integer.parseInt(cust);
        }
        String idCard = request.getParameter("idcard");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String note = request.getParameter("note");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String deposit = request.getParameter("deposit");
        Double money = null;
        if (deposit == null || deposit.trim().equals("")) {
            jsonObject.put("status", 440);
            return jsonObject.toJSONString();
        } else {
            try {
                money = Double.parseDouble(deposit);
            } catch (NumberFormatException e) {
                jsonObject.put("status", 441);
                return jsonObject.toJSONString();
            }
        }
        Date fromDate;
        Date toDate;
        if (from == null || from.equals("") || to == null || to.equals("")) {
            jsonObject.put("status", 430);
            return jsonObject.toJSONString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            fromDate = sdf.parse(from + " 12:00:00");
            toDate = sdf.parse(to + " 11:59:59");
        } catch (ParseException e) {
            jsonObject.put("status", 431);
            return jsonObject.toJSONString();
        }
        if (!IDCardValidate.validate(idCard)) {
            jsonObject.put("status", 400);
            return jsonObject.toJSONString();
        } else if (name == null || name.length() <= 0) {
            jsonObject.put("status", 410);
            return jsonObject.toJSONString();
        } else if (name.length() > 20) {
            jsonObject.put("status", 411);
            return jsonObject.toJSONString();
        } else if (tel == null || tel.length() <= 0) {
            jsonObject.put("status", 420);
            return jsonObject.toJSONString();
        } else if (tel.length() > 20) {
            jsonObject.put("status", 421);
            return jsonObject.toJSONString();
        } else if (toDate.before(fromDate)) {
            jsonObject.put("status", 432);
            return jsonObject.toJSONString();
        } else if (note.length() > 1024) {
            jsonObject.put("status", 450);
            return jsonObject.toJSONString();
        } else if (!isTimeOK(roomId, fromDate, toDate)) {
            jsonObject.put("status", 460);
            return jsonObject.toJSONString();
        } else {
            Customer customer;
            if (custId != null) {
                customer = customerService.getCustomerById(custId);
                if (!StringUtils.isNotBlank(customer.getName())
                        || (StringUtils.isNotBlank(customer.getName()) && !customer.getName().equals(name))) {
                    customer.setName(name);
                }
                if (!StringUtils.isNotBlank(customer.getTel())
                        || (StringUtils.isNotBlank(customer.getTel()) && !customer.getTel().equals(tel))) {
                    customer.setTel(tel);
                }
            } else {
                customer = new Customer();
                customer.setName(name);
                customer.setTel(tel);
                customer.setIdCard(idCard);
            }
            Reserve reserve = new Reserve();
            reserve.setCustomer(customer);
            reserve.setDeposit(money);
            reserve.setEntryDatetime(new Date());
            reserve.setEntryUser(loginUser);
            reserve.setEstCheckinDate(fromDate);
            reserve.setEstCheckoutDate(toDate);
            reserve.setNote(note);
            reserve.setReserveDate(new Date());
            reserve.setStatus(0);
            reserve.setRoom(room);
            reserveService.save(reserve);
            jsonObject.put("status", 200);

            return jsonObject.toJSONString();
        }
    }

    /**
     * 退定
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>退定成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>预定不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>退定金不正确</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>已入住</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>已退定</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>退定金额超限</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     */
    @RequestMapping(value = "unsubscribe", method = GET)
    @ResponseBody
    public String unsubscribe(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Integer id = null;
        Double refund = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            refund = Double.parseDouble(request.getParameter("refund"));
        } catch (NumberFormatException e) {
            json.put("status", 410);
            return json.toJSONString();
        }
        Reserve reserve = reserveService.getReserveById(id);
        if (reserve == null) {
            json.put("status", 400);
            return json.toJSONString();
        } else if (reserve.getStatus() == Reserve.CHECKEDIN) {
            json.put("status", 420);
            return json.toJSONString();
        } else if (reserve.getStatus() == Reserve.UNSUBSCRIBE) {
            json.put("status", 421);
            return json.toJSONString();
        } else if (reserve.getStatus() == Reserve.RESERVED) {
            if (reserve.getDeposit() < refund) {
                json.put("status", 430);
                return json.toJSONString();
            } else {
                reserve.setStatus(Reserve.UNSUBSCRIBE);
                reserve.setRefund(refund);
                reserve.setEntryDatetime(new Date());
                reserve.setEntryUser(userService.getUserById(user.getId()));
                try {
                    reserveService.update(reserve);
                    json.put("status", 200);
                    return json.toJSONString();
                } catch (SQLException e) {
                    json.put("status", 500);
                    return json.toJSONString();
                }
            }
        }
        return json.toJSONString();
    }

    /**
     * 入住
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>入住成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>身份证号没有填写姓名</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>填写入住人</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>押金不正确</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>押金为负数</td>
     * </tr>
     */
    @RequestMapping(value = "checkin", method = RequestMethod.POST)
    @ResponseBody
    public String checkin(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String[] idcards = request.getParameterValues("idcard");
        String[] name = request.getParameterValues("name");
        Integer roomId = Integer.parseInt(request.getParameter("roomId"));
        String note = request.getParameter("note");
        Double foregift = 0.0;
        try {
            foregift = Double.parseDouble(request.getParameter("foregift"));
            if (foregift < 0) {
                json.put("status", 421);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 420);
            return json.toJSONString();
        }
        if (note.length() > 1024) {
            json.put("status", 430);
            return json.toJSONString();
        }
        String reserveId = request.getParameter("reserveId");
        Reserve reserve = null;

        Checkin checkin = new Checkin();
        // 已预定的情况
        if (reserveId != null && !reserveId.equals("")) {
            reserve = reserveService.getReserveById(Integer.parseInt(reserveId));
            reserve.setStatus(Reserve.CHECKEDIN);
            checkin.setEstCheckoutDate(reserve.getEstCheckoutDate());
            checkin.setReserve(reserve);
        }
        // 未预定的情况
        else {
            String to = request.getParameter("toDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date toDate = null;
            try {
                toDate = sdf.parse(to + " 12:00:00");
            } catch (ParseException e) {
                json.put("status", 440);
                return json.toJSONString();
            }
            checkin.setEstCheckoutDate(toDate);
        }
        Set<Customer> customers = new HashSet<Customer>();
        for (int i = 0; i < idcards.length; i++) {
            if (idcards[i] == null || idcards[i].trim().equals("")) {
                continue;
            }
            if (name[i] == null || name[i].trim().equals("")) {
                json.put("status", 400);
                return json.toJSONString();
            }
            Customer customer = customerService.getCustomerByIdCard(idcards[i]);
            if (customer == null) {
                customer = new Customer();
                customer.setIdCard(idcards[i]);
            }
            if (!StringUtils.isNotBlank(customer.getName())
                    || (StringUtils.isNotBlank(customer.getName()) && !customer.getName().equals(name[i]))) {
                customer.setName(name[i]);
            }
            customers.add(customer);
        }

        if (customers.size() <= 0) {
            json.put("status", 410);
            return json.toJSONString();
        }
        RoomMaster roomMaster = roomMasterService.getRoomById(roomId);
        roomMaster.setStatus(RoomMaster.CHECKED_IN);
        checkin.setCustomers(customers);
        checkin.setRoom(roomMaster);
        checkin.setPrice(roomMaster.getPrice());
        checkin.setForegift(foregift);
        checkin.setCheckinDate(new Date());
        checkin.setEntryDatetime(new Date());
        checkin.setEntryUser(userService.getUserById(((User) request.getSession().getAttribute("loginUser")).getId()));
        checkin.setNote(note);
        checkinService.checkin(checkin);
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 清理房间
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>清理成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>清理失败</td>
     * </tr>
     */
    @RequestMapping(value = "cleanRoom", method = GET)
    @ResponseBody
    public String cleanRoom(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String id = request.getParameter("id");
        Integer roomId;
        try {
            roomId = Integer.parseInt(id);
            RoomMaster room = roomMasterService.getRoomById(roomId);
            room.setStatus(0);
            roomMasterService.updateRoom(room);
            json.put("status", 200);
        } catch (NumberFormatException e) {
            json.put("status", 400);
        } catch (SQLException e) {
            json.put("status", 400);
        }
        return json.toJSONString();
    }

    /**
     * 换房
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>换房成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>房间号不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>入住号不存在</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>原因过长</td>
     * </tr>
     */
    @RequestMapping(value = "changeRoom", method = GET)
    @ResponseBody
    public String changeRoom(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer toRoomId;
        Integer checkinId;
        String reason = request.getParameter("reason");
        User user = (User) request.getSession().getAttribute("loginUser");
        try {
            toRoomId = Integer.parseInt(request.getParameter("toRoomId"));
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            checkinId = Integer.parseInt(request.getParameter("checkinId"));
        } catch (NumberFormatException e) {
            json.put("status", 410);
            return json.toJSONString();
        }
        Checkin checkin = checkinService.getCheckinById(checkinId);
        if (checkin == null) {
            json.put("status", 410);
            return json.toJSONString();
        }
        RoomMaster toRoom = roomMasterService.getRoomById(toRoomId);
        if (toRoom == null) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (toRoom.getDelete()) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (reason != null && reason.length() > 1024) {
            json.put("status", 420);
            return json.toJSONString();
        }
        RoomMaster fromRoom = roomMasterService.getRoomById(checkin.getRoom().getId());
        fromRoom.setStatus(RoomMaster.CLEAN);
        toRoom.setStatus(RoomMaster.CHECKED_IN);
        user = userService.getUserById(user.getId());
        checkin.setEntryDatetime(new Date());
        checkin.setEntryUser(user);
        checkin.setRoom(toRoom);
        ChangeRoom changeRoom = new ChangeRoom();
        changeRoom.setFromRoom(fromRoom);
        changeRoom.setToRoom(toRoom);
        changeRoom.setReason(reason);
        changeRoom.setEntryDatetime(new Date());
        changeRoom.setEntryUser(user);
        Double diff = (toRoom.getPrice() - fromRoom.getPrice()) * DateUtil.dateDiffDays(new Date(), checkin.getEstCheckoutDate());
        changeRoom.setPriceDifference(diff);
        changeRoom.setCheckin(checkin);
        changeRoomService.save(changeRoom);
        json.put("status", 200);
        return json.toJSONString();
    }

    /**
     * 选择换房
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>选择通过</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>房间号不存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>入住号不存在</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>时间冲突</td>
     * </tr>
     */
    @RequestMapping(value = "chooseRoomById", method = GET)
    @ResponseBody
    public String chooseRoomById(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer roomId;
        Integer checkinId;
        Checkin checkin;
        try {
            roomId = Integer.parseInt(request.getParameter("roomId"));
        } catch (NumberFormatException e) {
            json.put("status", 400);
            return json.toJSONString();
        }
        try {
            checkinId = Integer.parseInt(request.getParameter("checkinId"));
            checkin = checkinService.getCheckinById(checkinId);
            if (checkin == null) {
                json.put("status", 410);
                return json.toJSONString();
            }
        } catch (NumberFormatException e) {
            json.put("status", 410);
            return json.toJSONString();
        }
        chooseRoom = roomMasterService.getRoomById(roomId);
        if (chooseRoom == null) {
            json.put("status", 400);
            return json.toJSONString();
        } else if (!isTimeOK(roomId, new Date(), checkin.getEstCheckoutDate())) {
            chooseRoom = null;
            json.put("status", 420);
            return json.toJSONString();
        } else {
            json.put("status", 200);
            return json.toJSONString();
        }
    }

    /**
     * 根据验证房间号是否存在
     *
     * @param request
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
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     */
    public Integer validateRoomNo(HttpServletRequest request, String method, String roomNo) {
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        RoomMaster room = (RoomMaster) request.getSession().getAttribute("room");
        if (roomNo == null || roomNo.length() <= 0) {
            return 400;
        } else if (roomNo.length() > 10) {
            return 401;
        } else {
            RoomMaster newRoom = roomMasterService.getRoomMasterByNo(hotel, roomNo);
            if (newRoom != null) {
                if (method.equals(Constraint.ADD) || method.equals(Constraint.DELETE) ||
                        (method.equals(Constraint.UPDATE) && room.getId() != newRoom.getId())) {
                    return 200;
                } else {
                    return 210;
                }
            } else {
                return 210;
            }
        }
    }

    /**
     * 修改当天的房间状态（主要是判断预定过的房间是不是已经进入已预定状态）
     *
     * @param rooms
     */
    public void changeStatus(List<SimpleRoom> rooms) {
        List<Reserve> reserves;
        Date now = new Date();
        for (int i = 0; i < rooms.size(); i++) {
            // 在入住和待清理状态不用操作其今日状态
            if (rooms.get(i).getStatus() == RoomMaster.CHECKED_IN || rooms.get(i).getStatus() == RoomMaster.CLEAN) {
                continue;
            }
            reserves = reserveService.getReserveByRoomId(rooms.get(i).getId(), Reserve.RESERVED, null, null);
            Boolean isReservedToday = false;
            for (int j = 0; j < reserves.size(); j++) {
                if (now.after(reserves.get(j).getEstCheckinDate()) && now.before(reserves.get(j).getEstCheckoutDate())) {
                    isReservedToday = true;
                    break;
                }
            }
            if (!isReservedToday) {
                rooms.get(i).setStatus(RoomMaster.FREE);
            } else {
                rooms.get(i).setStatus(RoomMaster.RESERVED);
            }
        }
    }

    /**
     * 判断预定或入住时间是否冲突
     *
     * @param roomId
     * @param start
     * @param end
     * @return
     */
    public Boolean isTimeOK(Integer roomId, Date start, Date end) {
        // 查询预定表，判断时间是否冲突
        List<Reserve> reserves = reserveService.getReserveByRoomId(roomId, Reserve.RESERVED, null, null);
        for (int i = 0; i < reserves.size(); i++) {
            Reserve reserve = reserves.get(i);
            /**
             * 以下为时间冲突的判断逻辑：
             * 已预定时间：         \________________\
             * 要预定时间：  \________\ \_______\ \______\
             */
            if (!((start.before(reserve.getEstCheckinDate()) && end.before(reserve.getEstCheckinDate()))
                    || start.after(reserve.getEstCheckoutDate()) && end.after(reserve.getEstCheckoutDate()))) {
                return false;
            }
        }
        // 查询入住表，判断时间是否冲突
        Checkin checkin = checkinService.getCheckinByRoomUncomplete(roomId);
        /**
         * 以下为时间冲突的判断逻辑：
         * 已预定时间：  \________________________\
         * 要预定时间：  \________\ \_______\ \______\
         * 以下为时间不冲突的判断逻辑：
         * 已预定时间：  \________\
         * 要预定时间：              \________\
         */
        if (checkin != null) {
            if (start.before(checkin.getEstCheckoutDate())) {
                return false;
            }
        }
        return true;
    }

}
