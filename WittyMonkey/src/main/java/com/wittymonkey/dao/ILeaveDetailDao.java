package com.wittymonkey.dao;

import com.wittymonkey.entity.LeaveDetail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/29.
 */
public interface ILeaveDetailDao extends IGenericDao<LeaveDetail, Serializable> {
    void delete(Integer id);

    List<LeaveDetail> getLeaveDetailByUserAndDateRange(Integer userId, Date from, Date to);
}
