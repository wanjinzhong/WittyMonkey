package com.wittymonkey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.ILeaveTypeDao;
import com.wittymonkey.entity.LeaveType;
import com.wittymonkey.service.ILeaveTypeService;

@Service(value="leaveTypeService")
public class LeaveTypeServiceImpl implements ILeaveTypeService{

	@Autowired
	private ILeaveTypeDao leaveTypeDao;
	@Override
	public void saveList(List<LeaveType> leaveTypes) {
		leaveTypeDao.saveList(leaveTypes);
	}

}
