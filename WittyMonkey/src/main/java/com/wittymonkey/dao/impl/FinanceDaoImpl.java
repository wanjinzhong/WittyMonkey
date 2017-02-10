package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.entity.Finance;

@Repository(value="financeDao")
public class FinanceDaoImpl extends GenericDaoImpl<Finance> implements IFinanceDao{

}
