package com.wittymonkey.service;

import com.wittymonkey.entity.LeaveType;

import java.util.List;

public interface ILeaveTypeService {

	void saveList(List<LeaveType> leaveTypes);

	void saveLeaveType(LeaveType leaveType);

	LeaveType getLeaveTypeById(Integer id);

	Integer getTotal(Integer hotelId);

	List<LeaveType> getLeaveTypes(Integer hotelId, Integer start, Integer total);

	LeaveType getLeaveTypeByName(Integer hotelId, String name);
}
