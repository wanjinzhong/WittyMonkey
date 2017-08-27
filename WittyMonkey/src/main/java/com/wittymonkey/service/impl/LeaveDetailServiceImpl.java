package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ILeaveDetailDao;
import com.wittymonkey.entity.LeaveDetail;
import com.wittymonkey.entity.LeaveHeader;
import com.wittymonkey.service.ILeaveDetailService;
import com.wittymonkey.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<LeaveDetail> getLeaveDetailByUserAndMonth(Integer userId, Date firstDayOfMonth) {
        Date firstDayOfLastMonth = DateUtil.nextMonthFirstDate(firstDayOfMonth);
        return leaveDetailDao.getLeaveDetailByUserAndDateRange(userId, firstDayOfMonth, firstDayOfLastMonth);
    }

    @Override
    public List<LeaveDetail> getLeaveDateilByUserInRange(Integer userId, Date from, Date to) {
        return leaveDetailDao.getLeaveDetailByUserAndDateRange(userId, from, to);
    }
}
