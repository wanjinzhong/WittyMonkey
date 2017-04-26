package com.wittymonkey.dao;

import com.wittymonkey.entity.Leave;

import java.io.Serializable;
import java.util.List;

public interface ILeaveDao extends IGenericDao<Leave, Serializable>{

    List<Leave> getLeaveByLeaveType(Integer typeId);

    Integer getTotalByStatus(Integer hotelId, Integer status);

    List<Leave> getLeaveByStatus(Integer hotelId, Integer type, Integer start, Integer total);
}
