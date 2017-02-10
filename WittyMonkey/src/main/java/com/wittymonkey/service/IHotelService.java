package com.wittymonkey.service;

import com.wittymonkey.entity.Hotel;

public interface IHotelService {
	public Hotel findHotelById(int id);
	
	public void saveHotel(Hotel hotel);
	
}
