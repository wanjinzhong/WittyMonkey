package com.wittymonkey.dao.impl;

import com.wittymonkey.entity.Hotel;
import com.wittymonkey.vo.Constriant;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.entity.FinanceType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="financeTypeDao")
public class FinanceTypeDaoImpl extends GenericDaoImpl<FinanceType> implements IFinanceTypeDao{

    @Override
    public List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from FinanceType where hotel.id = ? ");
        if (Constriant.FINANCE_TYPE_IN.equals(type)){
            hql.append(" and income = true");
        } else if (Constriant.FINANCE_TYPE_OUT.equals(type)){
            hql.append(" and income = false");
        }
        hql.append(" order by id");
        return queryListHQL(hql.toString(), hotelId, start, total);
    }

    @Override
    public Integer getTotal(Integer hotelId) {
        String hql = "select count(1) from FinanceType where hotel.id = ?";
        return countHQL(hql,hotelId);
    }

    @Override
    public FinanceType getFinanceTypeByName(Integer hotelId, String name) {
        String hql = "from FinanceType where hotel.id = :hotelId and name = :name";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("name", name);
        return queryOneHql(hql, param);
    }
}
