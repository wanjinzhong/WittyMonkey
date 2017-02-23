package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IReserveService;

@Service(value="reserveService")
public class ReserveServiceImpl implements IReserveService{

    @Autowired
    private IReserveDao reserveDao;

    @Override
    public void save(Reserve reserve) {
        reserveDao.save(reserve);
    }
}
