package com.wittymonkey.dao;

import com.wittymonkey.entity.SalaryHistory;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/21.
 */
public interface ISalaryHistoryDao extends IGenericDao<SalaryHistory, Serializable> {

    List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId);

    SalaryHistory getSalaryHistoryByUserIdAndSalaryDate(Integer userId, Date salaryDate);

    List<SalaryHistory> getSalaryByDateRange(Date from, Date to);

}
