package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILeaveDao;
import com.wittymonkey.entity.Leave;

import java.util.List;

@Repository(value="leaveDao")
public class LeaveDaoImpl extends GenericDaoImpl<Leave> implements ILeaveDao{

    @Override
    public List<Leave> getLeaveByType(Integer typeId) {
        String hql = "from Leave where leaveType.id = ?";
        return queryListHQL(hql, typeId);
    }
}
