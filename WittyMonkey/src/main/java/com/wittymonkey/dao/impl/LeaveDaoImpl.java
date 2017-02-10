package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILeaveDao;
import com.wittymonkey.entity.Leave;

@Repository(value="leaveDao")
public class LeaveDaoImpl extends GenericDaoImpl<Leave> implements ILeaveDao{

}
