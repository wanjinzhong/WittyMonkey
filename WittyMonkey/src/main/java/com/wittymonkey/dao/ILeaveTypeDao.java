package com.wittymonkey.dao;

import com.wittymonkey.entity.LeaveType;

import java.io.Serializable;
import java.util.List;

public interface ILeaveTypeDao extends IGenericDao<LeaveType, Serializable> {

    /**
     * 将请假类型集合一次性保存
     *
     * @param leaveTypes
     */
    void saveList(List<LeaveType> leaveTypes);

    LeaveType getLeaveTypeById(Integer id);

    Integer getTotal(Integer hotelId);

    List<LeaveType> getLeaveTypes(Integer hotelId, Integer start, Integer total);

    LeaveType getLeaveTypeByName(Integer hotelId, String name);
}
