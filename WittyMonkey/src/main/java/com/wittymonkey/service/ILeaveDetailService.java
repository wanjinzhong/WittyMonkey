package com.wittymonkey.service;

import com.wittymonkey.entity.LeaveDetail;
import com.wittymonkey.entity.LeaveHeader;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by neilw on 2017/4/29.
 */
public interface ILeaveDetailService {

    void deleteByLeaveHeader(LeaveHeader header);
}
