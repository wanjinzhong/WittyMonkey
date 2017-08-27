package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IFloorService;
import com.wittymonkey.util.ChangeToSimple;
import com.wittymonkey.vo.Constraint;
import com.wittymonkey.vo.SimpleFloor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/2/15.
 */
@Controller
public class FloorController {

    @Autowired
    private IFloorService floorService;

    @RequestMapping(value = "toAddFloor", method = RequestMethod.GET)
    public String toAddFloor(HttpServletRequest request) {
        return "floor_add";
    }

    @RequestMapping(value = "toEditFloor", method = RequestMethod.GET)
    public String toEditFloor(HttpServletRequest request) {
        Integer floorNo = Integer.parseInt(request.getParameter("floorNo"));
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Floor floor = floorService.getFloorByNo(hotel.getId(), floorNo);
        request.getSession().setAttribute("editFloor", floor);
        return "floor_edit";
    }

    /**
     * 保存楼层（包括添加和更新）
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
     * <td>楼层号存在</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>楼层输入错误</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>备注长度超限</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器出错</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "saveFloor", method = RequestMethod.GET)
    @ResponseBody
    public String saveFloor(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String floorNo = request.getParameter("floorNo");
        String note = request.getParameter("note");
        String method = request.getParameter("method");
        Floor editFloor = (Floor) request.getSession().getAttribute("editFloor");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        int status;
        int validateFloorNoRes = validateFloorNo(request, method, floorNo);
        if (validateFloorNoRes == 200) {
            status = 400;
        } else if (validateFloorNoRes == 400) {
            status = 401;
        } else {
            if (note.length() > 1024) {
                status = 410;
            } else {
                if (method.equals(Constraint.ADD)) {
                    Floor floor = new Floor();
                    floor.setHotel(hotel);
                    floor.setFloorNo(Integer.parseInt(floorNo));
                    floor.setNote(note);
                    floor.setEntryUser(user);
                    floor.setEntryDatetime(new Date());
                    floorService.saveFloor(floor);
                } else if (method.equals(Constraint.UPDATE)) {
                    Floor floor = floorService.getFloorByNo(hotel.getId(), editFloor.getFloorNo());
                    floor.setFloorNo(Integer.parseInt(floorNo));
                    floor.setNote(note);
                    floor.setEntryUser(user);
                    floor.setEntryDatetime(new Date());
                    try {
                        floorService.updateFloor(floor);
                    } catch (SQLException e) {
                        status = 500;
                    }
                }
                status = 200;
            }
        }
        json.put("status", status);
        return json.toJSONString();
    }

    /**
     * 删除楼层
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
     * <td>楼层号不存在</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "deleteFloor", method = RequestMethod.GET)
    @ResponseBody
    public String deleteFloor(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String floorNo = request.getParameter("floorNo");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        int status = 500;
        switch (validateFloorNo(request, Constraint.DELETE, floorNo)) {
            case 201:
                status = 400;
                break;
            case 400:
                status = 410;
                break;
            case 200:
                Floor floor = floorService.getFloorByNo(hotel.getId(), Integer.parseInt(floorNo));
                try {
                    floorService.deleteFloor(floor);
                } catch (SQLException e) {
                    status = 500;
                }
                status = 200;
                break;
        }
        json.put("status", status);
        return json.toJSONString();
    }

    @RequestMapping(value = "validateFloorNo", method = RequestMethod.GET)
    @ResponseBody
    public String validateFloorNo(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String floorNoStr = request.getParameter("floorNo");
        String method = request.getParameter("method");
        json.put("status", validateFloorNo(request, method, floorNoStr));
        return json.toJSONString();
    }

    @RequestMapping(value = "getFloor", method = RequestMethod.GET)
    @ResponseBody
    public String getFloor(HttpServletRequest request) {
        JSONArray json = new JSONArray();
        Hotel hotel = ((User) request.getSession().getAttribute("loginUser")).getHotel();
        List<Floor> floors = floorService.getFloorByHotel(hotel.getId(), null, null);
        Collections.sort(floors, new Comparator<Floor>() {
            @Override
            public int compare(Floor o1, Floor o2) {
                return o1.getFloorNo() - o2.getFloorNo();
            }
        });
        List<SimpleFloor> simpleFloors = ChangeToSimple.floorList(floors);
        json.addAll(simpleFloors);
        return json.toJSONString();
    }

    @RequestMapping(value = "getFloorByPage", method = RequestMethod.GET)
    @ResponseBody
    public String getFloorByPage(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        Integer curr = Integer.parseInt(request.getParameter("curr"));
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        Integer pageSize = loginUser.getSetting().getPageSize();
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        Integer count = floorService.getTotal(hotel.getId());
        List<Floor> floors = floorService.getFloorByHotel(hotel.getId(), (curr - 1) * pageSize, pageSize);
        Collections.sort(floors, new Comparator<Floor>() {
            @Override
            public int compare(Floor o1, Floor o2) {
                return o1.getFloorNo() - o2.getFloorNo();
            }
        });
        List<SimpleFloor> simpleFloors = ChangeToSimple.floorList(floors);
        json.put("count", count);
        json.put("pageSize", pageSize);
        JSONArray array = new JSONArray();
        array.addAll(simpleFloors);
        json.put("data", array);
        return json.toJSONString();
    }

    /**
     * 验证楼层号
     *
     * @param
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>楼层号存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>楼层号不存在</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>输入错误</td>
     * </tr>
     * <tr>
     * <td>500</td>
     * <td>服务器错误</td>
     * </tr>
     * </table>
     */
    public Integer validateFloorNo(HttpServletRequest request, String method, String floorNoStr) {
        Integer floorNo = null;
        Integer editFloorNo = null;
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        try {
            floorNo = Integer.parseInt(floorNoStr);
            if (Constraint.UPDATE.equals(method)) {
                editFloorNo = ((Floor) request.getSession().getAttribute("editFloor")).getFloorNo();
            }
        } catch (NumberFormatException e) {
            return 400;
        }
        if (floorService.isFloorExist(hotel.getId(), floorNo)) {
            if (Constraint.ADD.equals(method) || Constraint.DELETE.equals(method) ||
                    (Constraint.UPDATE.equals(method) && floorNo != editFloorNo)) {
                return 200;
            } else {
                return 201;
            }
        } else {
            return 201;
        }
    }
}
