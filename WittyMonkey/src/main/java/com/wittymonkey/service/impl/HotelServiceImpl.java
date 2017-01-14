package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IHotelDao;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.service.IHotelService;

@Service(value="hotelService")
public class HotelServiceImpl implements IHotelService {

	@Autowired
	private IHotelDao hotelDao;

	@Override
	public Hotel findHotelById(int id) {
		return hotelDao.findHotelById(id);
	}

}
