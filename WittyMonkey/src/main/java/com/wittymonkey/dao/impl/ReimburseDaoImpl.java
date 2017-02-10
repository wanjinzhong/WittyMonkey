package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IReimburseDao;
import com.wittymonkey.entity.Reimburse;

@Repository(value="reimburseDao")
public class ReimburseDaoImpl extends GenericDaoImpl<Reimburse> implements IReimburseDao{

}
