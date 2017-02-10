package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISalaryDao;
import com.wittymonkey.entity.Salary;

@Repository(value="salaryDao")
public class SalaryDaoImpl extends GenericDaoImpl<Salary> implements ISalaryDao{

}
