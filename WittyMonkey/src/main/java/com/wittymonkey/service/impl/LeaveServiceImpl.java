package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ILeaveDao;
import com.wittymonkey.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.ILeaveService;

import java.util.List;

@Service(value="leaveService")
public class LeaveServiceImpl implements ILeaveService{

    @Autowired
    private ILeaveDao leaveDao;

    @Override
    public Integer getTotalByStatus(Integer hotelId, Integer status) {
        return leaveDao.getTotalByStatus(hotelId, status);
    }

    @Override
    public List<Leave> getLeaveByStatus(Integer hotelId, Integer type, Integer start, Integer total) {
        return leaveDao.getLeaveByStatus(hotelId, type, start, total);
    }
}
