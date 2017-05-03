package com.wittymonkey.dao;

import com.wittymonkey.entity.Checkin;
import org.hibernate.annotations.Check;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ICheckinDao extends IGenericDao<Checkin, Serializable> {


    Checkin getCheckinByRoomUncomplete(Integer roomId);

    Checkin getCheckinById(Integer id);

    List<Checkin> getCheckinViaEstCheckoutDate(Integer hotelId, Date start, Date to);
}
