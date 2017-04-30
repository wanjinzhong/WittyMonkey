package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ILeaveDetailDao;
import com.wittymonkey.entity.LeaveDetail;
import com.wittymonkey.entity.LeaveHeader;
import com.wittymonkey.service.ILeaveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by neilw on 2017/4/29.
 */
@Service(value = "leaveDetailService")
public class LeaveDetailServiceImpl implements ILeaveDetailService {
    @Autowired
    private ILeaveDetailDao leaveDetailDao;

    @Override
    public void deleteByLeaveHeader(LeaveHeader header) {
        for (LeaveDetail detail : header.getLeaveDetails()) {
            leaveDetailDao.delete(detail.getId());
        }
    }
}
