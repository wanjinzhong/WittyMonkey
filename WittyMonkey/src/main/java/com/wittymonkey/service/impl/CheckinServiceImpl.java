package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.ICheckinService;

import java.sql.SQLException;

@Service(value="checkinService")
public class CheckinServiceImpl implements ICheckinService{

    @Autowired
    private ICheckinDao checkinDao;

    @Override
    public Checkin getCheckinByRoomUncomplete(Integer roomId) {
        return checkinDao.getCheckinByRoomUncomplete(roomId);
    }

    @Override
    public Checkin getCheckinById(Integer id) {
        return checkinDao.getCheckinById(id);
    }

    @Override
    public void update(Checkin checkin) throws SQLException {
        checkinDao.update(checkin);
    }

    @Override
    public void checkin(Checkin checkin) {
        checkinDao.save(checkin);
    }
}
