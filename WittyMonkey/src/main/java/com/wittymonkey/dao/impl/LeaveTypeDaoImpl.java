package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILeaveTypeDao;
import com.wittymonkey.entity.LeaveType;

@Repository(value="leaveTypeDao")
public class LeaveTypeDaoImpl extends GenericDaoImpl<LeaveType> implements ILeaveTypeDao{

}
