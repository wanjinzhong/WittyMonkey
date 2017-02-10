package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ILogDao;
import com.wittymonkey.entity.Log;

@Repository(value="logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements ILogDao{

}
