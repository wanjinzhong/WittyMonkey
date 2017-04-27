package com.wittymonkey.service;

import com.wittymonkey.entity.Salary;

import java.util.List;
import java.util.Map;

public interface ISalaryService {

    Integer getTotal(Map<Integer, Object> condition);

    List<Salary> getSalaryByPage(Map<Integer, Object> condition, Integer start, Integer total);

    Salary getSalaryById(Integer id);

    Salary getSalaryByStaffId(Integer staffId);
}