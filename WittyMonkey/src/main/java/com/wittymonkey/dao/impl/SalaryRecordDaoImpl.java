package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISalaryRecordDao;
import com.wittymonkey.entity.SalaryRecord;

@Repository(value="salaryRecordDao")
public class SalaryRecordDaoImpl extends GenericDaoImpl<SalaryRecord> implements ISalaryRecordDao{

}
