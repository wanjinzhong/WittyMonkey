package com.wittymonkey.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wittymonkey.entity.Hotel;
import com.wittymonkey.service.IHotelService;

@Controller
public class HotelController {

	@Autowired
	private IHotelService hotelService;
	@RequestMapping(name="hotel", method = RequestMethod.GET)
	public String hotel(HttpServletRequest request){
		int id = Integer.parseInt(request.getParameter("id"));
		Hotel hotel = hotelService.findHotelById(id);
		request.getSession().setAttribute("hotel", hotel);
		return "index";
	}
}
