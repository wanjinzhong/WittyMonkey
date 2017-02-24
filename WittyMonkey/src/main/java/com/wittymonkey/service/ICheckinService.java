package com.wittymonkey.service;

import com.wittymonkey.entity.Checkin;

public interface ICheckinService {

    Checkin getCheckinByRoom(Integer roomId);
}
