package com.wittymonkey.dao.impl;

import com.wittymonkey.vo.ConditionModel;
import com.wittymonkey.vo.Constraint;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISalaryDao;
import com.wittymonkey.entity.Salary;

import java.util.List;
import java.util.Map;

@Repository(value = "salaryDao")
public class SalaryDaoImpl extends GenericDaoImpl<Salary> implements ISalaryDao {

    @Override
    public Integer getTotal(Map<Integer, Object> condition) {
        StringBuffer hql = new StringBuffer("select count(1) from Salary");
        ConditionModel conditionModel = assymblyCondition(condition);
        hql.append(conditionModel.getHql());
        return countHQL(hql.toString(), conditionModel.getParam());
    }

    @Override
    public List<Salary> getSalaryByPage(Map<Integer, Object> condition, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from Salary");
        ConditionModel conditionModel = assymblyCondition(condition);
        hql.append(conditionModel.getHql());
        return queryListHQL(hql.toString(), conditionModel.getParam(), start, total);
    }

    private ConditionModel assymblyCondition(Map<Integer, Object> condition) {
        ConditionModel conditionModel = new ConditionModel();
        StringBuffer hql = new StringBuffer(" where");
        for (Map.Entry<Integer, Object> entry : condition.entrySet()) {
           if (Constraint.SALARY_SEARCH_CONDITION_HOTEL_ID.equals(entry.getKey())) {
                if (!conditionModel.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" staff.hotel.id = :hotelId");
                conditionModel.getParam().put("hotelId", entry.getValue());
            } else if (Constraint.SALARY_SEARCH_CONDITION_STAFF_NO.equals(entry.getKey())) {
                if (!conditionModel.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" staff.staffNo = :staffNo");
                conditionModel.getParam().put("staffNo", entry.getValue());
            } else if (Constraint.SALARY_SEARCH_CONDITION_STAFF_NAME.equals(entry.getKey())) {
               if (!conditionModel.getParam().isEmpty()) {
                   hql.append(" and");
               }
               hql.append(" staff.realName like '%" + entry.getValue() + "%'");
           } else if (Constraint.SALARY_SEARCH_CONDITION_INCUMBENT.equals(entry.getKey())) {
               if (!conditionModel.getParam().isEmpty()) {
                   hql.append(" and");
               }
               if ((Boolean) entry.getValue()) {
                   hql.append(" staff.dimissionDate is null");
               } else {
                   hql.append(" staff.dimissionDate is not null");
               }
           }
        }
        if (conditionModel.getParam().isEmpty()) {
            conditionModel.setHql("");
        } else {
            conditionModel.setHql(hql.toString());
        }
        return conditionModel;
    }
}
