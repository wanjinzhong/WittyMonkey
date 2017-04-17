package com.wittymonkey.dao.impl;

import com.wittymonkey.vo.Constriant;
import org.hibernate.mapping.Constraint;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.entity.Finance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="financeDao")
public class FinanceDaoImpl extends GenericDaoImpl<Finance> implements IFinanceDao{

    @Override
    public List<Finance> getFinanceByType(Integer typeId) {
        String hql = "from Finance where financeType.id = ?";
        return queryListHQL(hql, typeId);
    }

    @Override
    public Integer getTotal(Integer hotelId, Integer income) {
        StringBuffer hql = new StringBuffer("select count(1) from Finance where financeType.hotel.id = ? ");
        if (Constriant.FINANCE_IN.equals(income)){
            hql.append("and financeType.income = true ");
        } else if (Constriant.FINANCE_OUT.equals(income)){
            hql.append("and financeType.income = false ");
        }
        return countHQL(hql.toString(), hotelId);
    }

    @Override
    public List<Finance> getFinanceByPage(Integer hotelId, Integer income, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from Finance where financeType.hotel.id = ? ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (Constriant.FINANCE_IN.equals(income)){
            hql.append("and financeType.income = true ");
        } else if (Constriant.FINANCE_OUT.equals(income)){
            hql.append("and financeType.income = false ");
        }
        hql.append("order by entryDatetime");
        return queryListHQL(hql.toString(), hotelId, start, total);
    }
}
