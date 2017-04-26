package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

	@Override
	public LeaveType getLeaveTypeById(Integer id) {
		String hql = "from LeaveType where id = ?";
		return queryOneHql(hql, id);
	}

	@Override
	public Integer getTotal(Integer hotelId) {
		String hql = "select count(1) from LeaveType where hotel.id = ?";
		return countHQL(hql, hotelId);
	}

	@Override
	public List<LeaveType> getLeaveTypes(Integer hotelId, Integer start, Integer total) {
		String hql = "from LeaveType where hotel.id = ?";
		return queryListHQL(hql, hotelId, start, total);
	}

	@Override
	public LeaveType getLeaveTypeByName(Integer hotelId, String name) {
		String hql = "from LeaveType where hotel.id = :hotelId and name = :name";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("hotelId", hotelId);
		param.put("name", name);
		return queryOneHql(hql, param);
	}

}
