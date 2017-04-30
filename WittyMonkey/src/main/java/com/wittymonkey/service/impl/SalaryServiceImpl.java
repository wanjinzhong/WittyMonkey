package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ISalaryDao;
import com.wittymonkey.entity.Salary;
import com.wittymonkey.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "salaryService")
public class SalaryServiceImpl implements ISalaryService {

    @Autowired
    private ISalaryDao salaryDao;

    @Override
    public Integer getTotal(Map<Integer, Object> condition) {
        return salaryDao.getTotal(condition);
    }

    @Override
    public List<Salary> getSalaryByPage(Map<Integer, Object> condition, Integer start, Integer total) {
        return salaryDao.getSalaryByPage(condition, start, total);
    }

    @Override
    public Salary getSalaryById(Integer id) {
        return salaryDao.getSalaryById(id);
    }

    @Override
    public Salary getSalaryByStaffId(Integer staffId) {
        return salaryDao.getSalaryByStaffId(staffId);
    }
}
