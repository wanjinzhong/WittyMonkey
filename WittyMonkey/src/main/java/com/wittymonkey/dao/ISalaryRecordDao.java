package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wittymonkey.entity.Salary;
import com.wittymonkey.entity.SalaryRecord;

public interface ISalaryRecordDao extends IGenericDao<SalaryRecord, Serializable>{
    Integer getTotal(Integer salaryId);

    List<SalaryRecord> getSalaryRecordByPage(Integer salaryId, Integer start, Integer total);

    SalaryRecord getSalaryRecordByStartDate(Integer salaryId, Date startDate);

    SalaryRecord getSalaryRecordById(Integer id);

    SalaryRecord getSalaryRecordAtDate(Integer salaryId, Date date);

    List<SalaryRecord> getSalaryRecordByDateRange(Integer salaryId, Date startDate, Date endDate);
}
