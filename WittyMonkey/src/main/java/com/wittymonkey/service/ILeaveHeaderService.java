package com.wittymonkey.service;

import com.wittymonkey.entity.LeaveHeader;

import java.sql.SQLException;
import java.util.List;

public interface ILeaveHeaderService {
    Integer getTotalByStatus(Integer hotelId, Integer status);

    List<LeaveHeader> getLeaveHeaderByStatus(Integer hotelId, Integer type, Integer start, Integer total);

    Integer getTotalByUserAndStatus(Integer userId, Integer status);

    List<LeaveHeader> getLeaveHeaderByUserAndStatus(Integer userId, Integer type, Integer start, Integer total);

    void save(LeaveHeader header);

    LeaveHeader getLeaveHeaderById(Integer id);

    void delete(LeaveHeader header) throws SQLException;
}
