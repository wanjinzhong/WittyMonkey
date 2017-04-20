package com.wittymonkey.service;

import com.wittymonkey.entity.SalaryRecord;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ISalaryRecordService {

    Integer getTotal(Integer salaryId);

    List<SalaryRecord> getSalaryRecordByPage(Integer salaryId, Integer start, Integer total);

    SalaryRecord getSalaryRecordByStartDate(Integer salaryId, Date startDate);

    void save(SalaryRecord salaryRecord);

    SalaryRecord getSalaryRecordById(Integer id);

    void delete(SalaryRecord salaryRecord) throws SQLException;
}
