package com.wittymonkey.service;

import com.wittymonkey.entity.SalaryHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/21.
 */
public interface ISalaryHistoryService {
    List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId);

    SalaryHistory getSalaryHistoryByUserIdAndSalaryDate(Integer userId, Date salaryDate);

    void batchSave(List<SalaryHistory> salaryHistories);
}
