package com.wittymonkey.dao;

import java.io.Serializable;

import com.wittymonkey.entity.Hotel;

public interface IHotelDao extends IGenericDao<Hotel, Serializable>{
	/**
	 * 根据id获取Hotel
	 * @param id
	 * @return
	 */
	public Hotel findHotelById(int id);
}
