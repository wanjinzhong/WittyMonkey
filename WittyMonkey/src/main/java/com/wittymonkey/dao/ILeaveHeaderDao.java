package com.wittymonkey.dao;

import com.wittymonkey.entity.LeaveHeader;

import java.io.Serializable;
import java.util.List;

public interface ILeaveHeaderDao extends IGenericDao<LeaveHeader, Serializable>{

    List<LeaveHeader> getLeaveHeaderByLeaveType(Integer typeId);

    Integer getTotalByStatus(Integer hotelId, Integer status);

    List<LeaveHeader> getLeaveHeaderByStatus(Integer hotelId, Integer type, Integer start, Integer total);

    LeaveHeader getLeaveHeaderById(Integer id);
}
