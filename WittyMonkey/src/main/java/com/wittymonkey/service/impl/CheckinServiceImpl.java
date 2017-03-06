package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.ICheckinService;

@Service(value="checkinService")
public class CheckinServiceImpl implements ICheckinService{

    @Autowired
    private ICheckinDao checkinDao;

    @Override
    public Checkin getCheckinByRoom(Integer roomId) {
        return checkinDao.getCheckinByRoom(roomId);
    }

    @Override
    public void checkin(Checkin checkin) {
        checkinDao.save(checkin);
    }
}
