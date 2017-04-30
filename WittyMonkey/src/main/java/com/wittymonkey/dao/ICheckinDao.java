package com.wittymonkey.dao;

import com.wittymonkey.entity.Checkin;

import java.io.Serializable;

public interface ICheckinDao extends IGenericDao<Checkin, Serializable> {


    Checkin getCheckinByRoomUncomplete(Integer roomId);

    Checkin getCheckinById(Integer id);
}
