package com.wittymonkey.service;

import com.wittymonkey.entity.LeaveHeader;

/**
 * Created by neilw on 2017/4/29.
 */
public interface ILeaveDetailService {

    void deleteByLeaveHeader(LeaveHeader header);
}
