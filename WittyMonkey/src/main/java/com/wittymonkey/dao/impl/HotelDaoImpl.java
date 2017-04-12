package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IHotelDao;
import com.wittymonkey.entity.Hotel;

@Repository(value="hotelDao")
public class HotelDaoImpl extends GenericDaoImpl<Hotel> implements IHotelDao {

	@Override
	public Hotel findHotelById(int id) {
		String hql = "from Hotel where id = ?";
		return queryOneHql(hql, id);
	}

	@Override
	public void saveHotel(Hotel hotel) {
		save(hotel);
	}

}
