package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ISalaryHistoryDao;
import com.wittymonkey.entity.SalaryHistory;
import com.wittymonkey.service.ISalaryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/21.
 */
@Service(value = "salaryHistorySerivice")
public class SalaryHistoryServiceImpl implements ISalaryHistoryService {

    @Autowired
    private ISalaryHistoryDao salaryHistoryDao;

    @Override
    public List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId) {
        return salaryHistoryDao.getSalaryHistoryBySalaryId(salaryId);
    }

    @Override
    public SalaryHistory getSalaryHistoryByUserIdAndSalaryDate(Integer userId, Date salaryDate) {
        return salaryHistoryDao.getSalaryHistoryByUserIdAndSalaryDate(userId, salaryDate);
    }

    @Override
    public List<SalaryHistory> getSalaryByDateRange(Date from, Date to) {
        return salaryHistoryDao.getSalaryByDateRange(from, to);
    }

    @Override
    public void batchSave(List<SalaryHistory> salaryHistories) {
        for (SalaryHistory history : salaryHistories){
            salaryHistoryDao.save(history);
        }
    }

    @Override
    public void save(SalaryHistory salaryHistory) {
        salaryHistoryDao.save(salaryHistory);
    }
}
