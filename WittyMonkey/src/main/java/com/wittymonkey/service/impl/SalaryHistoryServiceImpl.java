package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ISalaryHistoryDao;
import com.wittymonkey.entity.SalaryHistory;
import com.wittymonkey.service.ISalaryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
