package com.wittymonkey.dao;

import com.wittymonkey.entity.Hotel;

import java.io.Serializable;

public interface IHotelDao extends IGenericDao<Hotel, Serializable> {
    /**
     * 根据id获取Hotel
     *
     * @param id
     * @return
     */
    public Hotel findHotelById(int id);

    /**
     * 保存hotel
     *
     * @param hotel
     */
    public void saveHotel(Hotel hotel);

}
