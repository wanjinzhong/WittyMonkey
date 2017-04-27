package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ILeaveHeaderDao;
import com.wittymonkey.dao.ILeaveTypeDao;
import com.wittymonkey.entity.LeaveHeader;
import com.wittymonkey.entity.LeaveType;
import com.wittymonkey.service.ILeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service(value="leaveTypeService")
public class LeaveTypeServiceImpl implements ILeaveTypeService{

	@Autowired
	private ILeaveTypeDao leaveTypeDao;

	@Autowired
	private ILeaveHeaderDao leaveHeaderDao;

	@Override
	public void saveList(List<LeaveType> leaveTypes) {
		leaveTypeDao.saveList(leaveTypes);
	}

	@Override
	public void saveLeaveType(LeaveType leaveType) {
		leaveTypeDao.save(leaveType);
	}

	@Override
	public LeaveType getLeaveTypeById(Integer id) {
		return leaveTypeDao.getLeaveTypeById(id);
	}

	@Override
	public Integer getTotal(Integer hotelId) {
		return leaveTypeDao.getTotal(hotelId);
	}

	@Override
	public List<LeaveType> getLeaveTypes(Integer hotelId, Integer start, Integer total) {
		return leaveTypeDao.getLeaveTypes(hotelId, start, total);
	}

	@Override
	public LeaveType getLeaveTypeByName(Integer hotelId, String name) {
		return leaveTypeDao.getLeaveTypeByName(hotelId, name);
	}

	@Override
	public void delete(LeaveType leaveType) throws SQLException {
		List<LeaveHeader> leaves = leaveHeaderDao.getLeaveHeaderByLeaveType(leaveType.getId());
		for (LeaveHeader leave : leaves){
			leave.setLeaveType(null);
			leaveHeaderDao.save(leave);
		}
		leaveTypeDao.delete(leaveType);
	}

}
