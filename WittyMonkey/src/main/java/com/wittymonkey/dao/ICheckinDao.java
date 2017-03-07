package com.wittymonkey.dao;

import java.io.Serializable;

import com.wittymonkey.entity.Checkin;

public interface ICheckinDao extends IGenericDao<Checkin, Serializable>{


    Checkin getCheckinByRoomUncomplete(Integer roomId);

    Checkin getCheckinById(Integer id);
}
