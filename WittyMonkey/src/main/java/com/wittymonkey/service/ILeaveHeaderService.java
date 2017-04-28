package com.wittymonkey.service;

import com.wittymonkey.entity.LeaveHeader;

import java.util.List;

public interface ILeaveHeaderService {
    Integer getTotalByStatus(Integer hotelId, Integer status);

    List<LeaveHeader> getLeaveHeaderByStatus(Integer hotelId, Integer type, Integer start, Integer total);

    void save(LeaveHeader header);
}
