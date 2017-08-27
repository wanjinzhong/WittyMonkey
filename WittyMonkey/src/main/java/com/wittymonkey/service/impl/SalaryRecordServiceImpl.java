package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ISalaryRecordDao;
import com.wittymonkey.entity.SalaryRecord;
import com.wittymonkey.service.ISalaryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service(value = "salaryRecordService")
public class SalaryRecordServiceImpl implements ISalaryRecordService {

    @Autowired
    private ISalaryRecordDao salaryRecordDao;

    @Override
    public Integer getTotal(Integer salaryId) {
        return salaryRecordDao.getTotal(salaryId);
    }

    @Override
    public List<SalaryRecord> getSalaryRecordByPage(Integer salaryId, Integer start, Integer total) {
        return salaryRecordDao.getSalaryRecordByPage(salaryId, start, total);
    }

    @Override
    public SalaryRecord getSalaryRecordByStartDate(Integer salaryId, Date startDate) {
        return salaryRecordDao.getSalaryRecordByStartDate(salaryId, startDate);
    }

    @Override
    public SalaryRecord getSalaryRecordAtDate(Integer salaryId, Date date) {
        return salaryRecordDao.getSalaryRecordAtDate(salaryId, date);
    }

    @Override
    public void save(SalaryRecord salaryRecord) {
        salaryRecordDao.save(salaryRecord);
    }

    @Override
    public SalaryRecord getSalaryRecordById(Integer id) {
        return salaryRecordDao.getSalaryRecordById(id);
    }

    @Override
    public List<SalaryRecord> getSalaryRecordByDateRange(Integer salaryId, Date startDate, Date endDate) {
        return salaryRecordDao.getSalaryRecordByDateRange(salaryId, startDate, endDate);
    }

    @Override
    public void delete(SalaryRecord salaryRecord) throws SQLException {
        salaryRecordDao.delete(salaryRecord);
    }
}
