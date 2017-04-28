package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ILeaveHeaderDao;
import com.wittymonkey.entity.LeaveHeader;
import com.wittymonkey.service.ILeaveHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value="leaveHeaderService")
public class LeaveHeaderServiceImpl implements ILeaveHeaderService {

    @Autowired
    private ILeaveHeaderDao leaveHeaderDao;

    @Override
    public Integer getTotalByStatus(Integer hotelId, Integer status) {
        return leaveHeaderDao.getTotalByStatus(hotelId, status);
    }

    @Override
    public List<LeaveHeader> getLeaveHeaderByStatus(Integer hotelId, Integer type, Integer start, Integer total) {
        return leaveHeaderDao.getLeaveHeaderByStatus(hotelId, type, start, total);
    }

    @Override
    public void save(LeaveHeader header) {
        leaveHeaderDao.save(header);
    }
}
