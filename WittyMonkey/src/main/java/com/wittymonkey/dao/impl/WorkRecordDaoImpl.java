package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IWorkRecordDao;
import com.wittymonkey.entity.WorkRecord;

@Repository(value="workRecordDao")
public class WorkRecordDaoImpl extends GenericDaoImpl<WorkRecord> implements IWorkRecordDao{

}
