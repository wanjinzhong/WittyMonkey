package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by neilw on 2017/2/15.
 */
@Controller
public class FloorController {

    @Autowired
    private IFloorService floorService;

    @RequestMapping(value = "toAddFloor", method = RequestMethod.GET)
    public String toAddFloor(HttpServletRequest request){
        return "floor_add";
    }

    /**
     * 添加楼层
     * @param request
     * @return
     * <table border="1" cellspacing="0">
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
     * </table>
     */
    @RequestMapping(value = "addFloor", method = RequestMethod.GET)
    @ResponseBody
    public String addFloor(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String floorNo = request.getParameter("floorNo");
        String note = request.getParameter("note");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        User user = (User) request.getSession().getAttribute("loginUser");
        int status;
        int validateFloorNoRes = validateFloorNo(hotel,floorNo);
        if (validateFloorNoRes == 200){
            status = 400;
        } else if (validateFloorNoRes == 400) {
            status = 401;
        } else {
            if (note.length() > 1024){
                status = 410;
            } else {
                Floor floor = new Floor();
                floor.setHotel(hotel);
                floor.setFloorNo(Integer.parseInt(floorNo));
                floor.setNote(note);
                floor.setEntryUser(user);
                floor.setEntryDatetime(new Date());
                floorService.saveFloor(floor);
                status = 200;
            }
        }
        json.put("status", status);
        return json.toJSONString();
    }


    @RequestMapping(value = "validateFloorNo", method = RequestMethod.GET)
    @ResponseBody
    public String validateFloorNo(HttpServletRequest request){
        JSONObject json = new JSONObject();
        String floorNoStr = request.getParameter("floorNo");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        json.put("status", validateFloorNo(hotel,floorNoStr));
        return json.toJSONString();
    }

    /**
     * 验证楼层号
     * @param
     * @return
     * <table border="1" cellspacing="0">
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
     * </table>
     */
    public Integer validateFloorNo(Hotel hotel, String floorNoStr){
        Integer floorNo = null;
        try {
            floorNo = Integer.parseInt(floorNoStr);
        } catch (NumberFormatException e) {
            return 400;
        }
        if (floorService.isFloorExist(hotel.getId(), floorNo)){
           return 200;
        } else{
            return 201;
        }
    }
}
