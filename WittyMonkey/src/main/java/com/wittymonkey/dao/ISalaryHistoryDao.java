package com.wittymonkey.dao;

import com.wittymonkey.entity.SalaryHistory;

import java.util.List;

/**
 * Created by neilw on 2017/4/21.
 */
public interface ISalaryHistoryDao {

    List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId);
}
