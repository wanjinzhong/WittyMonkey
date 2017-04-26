package com.wittymonkey.service;

import com.wittymonkey.entity.Leave;

import java.util.List;

public interface ILeaveService {
    Integer getTotalByStatus(Integer hotelId, Integer status);

    List<Leave> getLeaveByStatus(Integer hotelId, Integer type, Integer start, Integer total);
}
