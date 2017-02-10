package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IHotelDao;
import com.wittymonkey.entity.Hotel;

@Repository(value="hotelDao")
public class HotelDaoImpl extends GenericDaoImpl<Hotel> implements IHotelDao {

	@Override
	public Hotel findHotelById(int id) {
		return (Hotel) this.load(id);
	}

	@Override
	public void saveHotel(Hotel hotel) {
		save(hotel);
	}

}
