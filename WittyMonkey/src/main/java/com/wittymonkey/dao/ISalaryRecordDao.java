package com.wittymonkey.dao;

import com.wittymonkey.entity.SalaryRecord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ISalaryRecordDao extends IGenericDao<SalaryRecord, Serializable> {
    Integer getTotal(Integer salaryId);

    List<SalaryRecord> getSalaryRecordByPage(Integer salaryId, Integer start, Integer total);

    SalaryRecord getSalaryRecordByStartDate(Integer salaryId, Date startDate);

    SalaryRecord getSalaryRecordById(Integer id);

    SalaryRecord getSalaryRecordAtDate(Integer salaryId, Date date);

    List<SalaryRecord> getSalaryRecordByDateRange(Integer salaryId, Date startDate, Date endDate);
}
