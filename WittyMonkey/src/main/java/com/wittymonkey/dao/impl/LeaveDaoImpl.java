package com.wittymonkey.dao.impl;

import com.wittymonkey.vo.Constraint;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILeaveDao;
import com.wittymonkey.entity.Leave;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="leaveDao")
public class LeaveDaoImpl extends GenericDaoImpl<Leave> implements ILeaveDao{

    @Override
    public List<Leave> getLeaveByLeaveType(Integer typeId) {
        String hql = "from Leave where leaveType.id = ?";
        return queryListHQL(hql, typeId);
    }

    @Override
    public Integer getTotalByStatus(Integer hotelId, Integer status) {
        String hql = "select count(1) from Leave where leaveType.hotel.id = :hotelId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hotelId", hotelId);
        if (Constraint.LEAVE_SEARCHTYPE_PENDING.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_APPROVED.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_REJECTED.equals(status)){
            hql += " and status = :status";
            map.put("status", status);
        }
        return countHQL(hql,map);
    }

    @Override
    public List<Leave> getLeaveByStatus(Integer hotelId, Integer status, Integer start, Integer total) {
        String hql = "from Leave where leaveType.hotel.id = :hotelId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hotelId", hotelId);
        if (Constraint.LEAVE_SEARCHTYPE_PENDING.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_APPROVED.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_REJECTED.equals(status)){
            hql += " and status = :status";
            map.put("status", status);
        }
        return queryListHQL(hql,map);
    }
}
