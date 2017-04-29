package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.ILeaveHeaderDao;
import com.wittymonkey.entity.LeaveHeader;
import com.wittymonkey.vo.Constraint;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="leaveHeaderDao")
public class LeaveHeaderDaoImpl extends GenericDaoImpl<LeaveHeader> implements ILeaveHeaderDao {

    @Override
    public List<LeaveHeader> getLeaveHeaderByLeaveType(Integer typeId) {
        String hql = "from LeaveHeader where leaveType.id = ?";
        return queryListHQL(hql, typeId);
    }

    @Override
    public Integer getTotalByStatus(Integer hotelId, Integer status) {
        String hql = "select count(1) from LeaveHeader where leaveType.hotel.id = :hotelId";
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
    public Integer getTotalByUserAndStatus(Integer userId, Integer status) {
        String hql = "select count(1) from LeaveHeader where applyUser.id= :userId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        if (Constraint.LEAVE_SEARCHTYPE_PENDING.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_APPROVED.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_REJECTED.equals(status)){
            hql += " and status = :status";
            map.put("status", status);
        }
        return countHQL(hql,map);
    }

    @Override
    public List<LeaveHeader> getLeaveHeaderByStatus(Integer hotelId, Integer status, Integer start, Integer total) {
        String hql = "from LeaveHeader where leaveType.hotel.id = :hotelId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hotelId", hotelId);
        if (Constraint.LEAVE_SEARCHTYPE_PENDING.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_APPROVED.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_REJECTED.equals(status)){
            hql += " and status = :status";
            map.put("status", status);
        }
        hql += " order by id desc";
        return queryListHQL(hql,map);
    }

    @Override
    public List<LeaveHeader> getLeaveHeaderByUserAndStatus(Integer userId, Integer status, Integer start, Integer total) {
        String hql = "from LeaveHeader where applyUser.id = :userId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        if (Constraint.LEAVE_SEARCHTYPE_PENDING.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_APPROVED.equals(status) ||
                Constraint.LEAVE_SEARCHTYPE_REJECTED.equals(status)){
            hql += " and status = :status";
            map.put("status", status);
        }
        hql += " order by id desc";
        return queryListHQL(hql,map);
    }

    @Override
    public LeaveHeader getLeaveHeaderById(Integer id) {
        String hql = "from LeaveHeader where id = ?";
        return queryOneHql(hql, id);
    }
}
