package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;
import com.wittymonkey.service.IReserveService;
import com.wittymonkey.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service(value = "reserveService")
public class ReserveServiceImpl implements IReserveService {

    @Autowired
    private IReserveDao reserveDao;

    @Override
    public Reserve getReserveById(Integer reserveId) {
        return reserveDao.getReserveById(reserveId);
    }

    @Override
    public void save(Reserve reserve) {
        reserveDao.save(reserve);
    }

    @Override
    public List<Reserve> getReserveByRoomId(Integer roomId, Integer status, Integer start, Integer pageSize) {
        return reserveDao.getReserveByRoomId(roomId, status, start, pageSize);
    }

    @Override
    public Reserve getReserveByDate(Integer roomId, Integer status, Date date) {
        return reserveDao.getReserveByDate(roomId, status, date);
    }

    @Override
    public Integer getTotalByRoomIdReserved(Integer roomId, Integer status) {
        return reserveDao.getTotalByRoomIdReserved(roomId, status);
    }

    @Override
    public void update(Reserve reserve) throws SQLException {
        reserveDao.update(reserve);
    }

    @Override
    public List<Reserve> getReservesViaEstCheckIn(Integer hotelId, Date date) {
        Date dayStart = DateUtil.dayStart(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return reserveDao.getReservesViaEstCheckIn(hotelId, dayStart, calendar.getTime());
    }
}
