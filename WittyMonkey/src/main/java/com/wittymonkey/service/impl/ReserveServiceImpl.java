package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IReserveService;

import java.util.Date;
import java.util.List;

@Service(value="reserveService")
public class ReserveServiceImpl implements IReserveService{

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
    public Reserve getReserveByDate(Integer roomId, Date date) {
        return reserveDao.getReserveByDate(roomId, date);
    }

    @Override
    public Integer getTotalByRoomIdReserved(Integer roomId, Integer status) {
        return reserveDao.getTotalByRoomIdReserved(roomId, status);
    }
}
