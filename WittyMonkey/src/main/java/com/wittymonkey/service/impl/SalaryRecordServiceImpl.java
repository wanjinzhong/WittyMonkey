package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ISalaryDao;
import com.wittymonkey.dao.ISalaryRecordDao;
import com.wittymonkey.entity.SalaryRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.ISalaryRecordService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service(value="salaryRecordService")
public class SalaryRecordServiceImpl implements ISalaryRecordService{

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
    public void save(SalaryRecord salaryRecord) {
        salaryRecordDao.save(salaryRecord);
    }

    @Override
    public SalaryRecord getSalaryRecordById(Integer id) {
        return salaryRecordDao.getSalaryRecordById(id);
    }

    @Override
    public void delete(SalaryRecord salaryRecord) throws SQLException {
        salaryRecordDao.delete(salaryRecord);
    }
}
