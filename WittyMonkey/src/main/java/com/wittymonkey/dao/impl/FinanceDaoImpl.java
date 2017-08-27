package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.entity.Finance;

import java.util.*;

@Repository(value = "financeDao")
public class FinanceDaoImpl extends GenericDaoImpl<Finance> implements IFinanceDao {

    @Override
    public List<Finance> getFinanceByType(Integer typeId) {
        String hql = "from Finance where financeType.id = ?";
        return queryListHQL(hql, typeId);
    }

    @Override
    public Integer getTotal(Integer hotelId, Integer type, Date from, Date to) {
        StringBuffer hql = new StringBuffer("select count(1) from Finance where financeType.hotel.id = :hotelId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (new Integer(-2).equals(type)){
            hql.append("and financeType.income = true ");
        } else if (new Integer(-1).equals(type)){
            hql.append("and financeType.income = false ");
        } else if (type > 0){
            hql.append("and financeType.id = :type ");
            param.put("type", type);
        }
        if (from != null){
            hql.append(" and entryDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append(" and entryDatetime < :to ");
            param.put("to", to);
        }
        param.put("hotelId", hotelId);
        return countHQL(hql.toString(), param);
    }

    @Override
    public List<Finance> getFinanceByPage(Integer hotelId, Integer type, Date from, Date to, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from Finance where financeType.hotel.id = :hotelId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (new Integer(-2).equals(type)){
            hql.append("and financeType.income = true ");
        } else if (new Integer(-1).equals(type)){
            hql.append("and financeType.income = false ");
        } else if (type > 0){
            hql.append("and financeType.id = :type ");
            param.put("type", type);
        }
        if (from != null){
            hql.append(" and entryDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append(" and entryDatetime < :to ");
            param.put("to", to);
        }
        hql.append("order by entryDatetime desc");
        param.put("hotelId", hotelId);
        return queryListHQL(hql.toString(), param, start, total);
    }
}
