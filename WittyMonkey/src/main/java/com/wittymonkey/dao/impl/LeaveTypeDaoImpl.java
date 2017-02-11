package com.wittymonkey.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILeaveTypeDao;
import com.wittymonkey.entity.LeaveType;

@Repository(value="leaveTypeDao")
public class LeaveTypeDaoImpl extends GenericDaoImpl<LeaveType> implements ILeaveTypeDao{

	@Override
	public void saveList(List<LeaveType> leaveTypes) {
		Iterator<LeaveType> it = leaveTypes.iterator();
		if (it.hasNext()){
			save(it.next());
		}
	}

}
