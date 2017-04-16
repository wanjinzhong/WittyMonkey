package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.entity.Finance;

import java.util.List;

@Repository(value="financeDao")
public class FinanceDaoImpl extends GenericDaoImpl<Finance> implements IFinanceDao{

    @Override
    public List<Finance> getFinanceByType(Integer typeId) {
        String hql = "from Finance where financeType.id = ?";
        return queryListHQL(hql, typeId);
    }
}
