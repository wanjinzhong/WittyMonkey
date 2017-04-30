package com.wittymonkey.service;

import com.wittymonkey.entity.Hotel;

public interface IHotelService {
    Hotel findHotelById(int id);

    void saveHotel(Hotel hotel);

}
