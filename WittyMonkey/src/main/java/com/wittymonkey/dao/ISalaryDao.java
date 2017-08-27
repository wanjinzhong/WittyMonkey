package com.wittymonkey.dao;

import com.wittymonkey.entity.Salary;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface ISalaryDao extends IGenericDao<Salary, Serializable> {

    Integer getTotal(Map<Integer, Object> condition);

    List<Salary> getSalaryByPage(Map<Integer, Object> condition, Integer start, Integer total);

    Salary getSalaryById(Integer id);

    Salary getSalaryByStaffId(Integer staffId);
}
