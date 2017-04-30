package com.wittymonkey.dao;

import com.wittymonkey.entity.LeaveDetail;

import java.io.Serializable;

/**
 * Created by neilw on 2017/4/29.
 */
public interface ILeaveDetailDao extends IGenericDao<LeaveDetail, Serializable> {
    void delete(Integer id);
}
