package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.ILeaveDetailDao;
import com.wittymonkey.entity.LeaveDetail;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neilw on 2017/4/29.
 */
@Repository(value = "leaveDetailDao")
public class LeaveDetailDaoImpl extends GenericDaoImpl<LeaveDetail> implements ILeaveDetailDao{
    @Override
    public void delete(Integer id) {
        String hql = "delete from LeaveDetail where id = :id";
        Query query = this.getCurrentSession().createQuery(hql.toString()).setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<LeaveDetail> getLeaveDetailByUserAndDateRange(Integer userId, Date from, Date to) {
        String hql = "from LeaveDetail where leaveHeader.applyUser.id = :userId and fromDate >= :from and toDate < :to";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("from", from);
        param.put("to", to);
        return queryListHQL(hql,param);
    }
}
