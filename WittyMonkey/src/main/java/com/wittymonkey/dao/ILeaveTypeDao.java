package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.LeaveType;

public interface ILeaveTypeDao extends IGenericDao<LeaveType, Serializable>{

	/**
	 * 将请假类型集合一次性保存
	 * @param leaveTypes
	 */
	public void saveList(List<LeaveType> leaveTypes);
}
