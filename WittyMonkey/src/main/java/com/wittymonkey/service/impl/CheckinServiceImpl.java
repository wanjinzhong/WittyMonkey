package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;
import com.wittymonkey.service.ICheckinService;
import com.wittymonkey.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(value = "checkinService")
public class CheckinServiceImpl implements ICheckinService {

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

    @Override
    public List<Checkin> getCheckinViaEstCheckoutDate(Integer hotelId, Date date) {
        Date dayStart = DateUtil.dayStart(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return checkinDao.getCheckinViaEstCheckoutDate(hotelId, dayStart, calendar.getTime());
    }
}
