package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.entity.FinanceType;

@Repository(value="financeTypeDao")
public class FinanceTypeDaoImpl extends GenericDaoImpl<FinanceType> implements IFinanceTypeDao{

}
