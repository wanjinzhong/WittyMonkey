package com.wittymonkey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by neilw on 2017/2/20.
 */
@Controller
public class RoomController {

    @RequestMapping(value = "toAddRoom", method = RequestMethod.GET)
    public String toAddRoom(HttpServletRequest request){
        return "room_add";
    }
}
