package com.wittymonkey.dao.impl;

import com.wittymonkey.entity.InStock;
import com.wittymonkey.vo.ConditionModel;
import com.wittymonkey.vo.Constraint;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IOutStockDao;
import com.wittymonkey.entity.OutStock;

import java.util.List;
import java.util.Map;

@Repository(value="outStockDao")
public class OutStockDaoImpl extends GenericDaoImpl<OutStock> implements IOutStockDao{

    @Override
    public List<OutStock> getOutStockByMateriel(Integer materielId) {
        String hql = "from OutStock where materiel.id = ?";
        return queryListHQL(hql, materielId);
    }


    @Override
    public Integer getTotal(Map<Integer, Object> param) {
        String hql = "select count(1) from OutStock";
        ConditionModel condition = assymblyCondition(param);
        hql += condition.getHql();
        return countHQL(hql, condition.getParam());
    }

    @Override
    public List<OutStock> getOutStockByCondition(Map<Integer, Object> param, Integer start, Integer total) {
        String hql = "from OutStock";
        ConditionModel condition = assymblyCondition(param);
        hql += condition.getHql() + " order by entryDatetime desc";
        return queryListHQL(hql, condition.getParam());
    }

    public ConditionModel assymblyCondition(Map<Integer, Object> param) {
        ConditionModel condition = new ConditionModel();
        StringBuilder hql = new StringBuilder(" where");
        for (Map.Entry<Integer, Object> entry : param.entrySet()) {
            if (Constraint.OUTSTOCK_SEARCH_CONDITION_HOTEL_ID.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" hotel.id = :hotelId");
                condition.getParam().put("hotelId", entry.getValue());
            }
            if (Constraint.OUTSTOCK_SEARCH_CONDITION_TYPE_ID.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" materiel.materielType.id = :typeId");
                condition.getParam().put("typeId", entry.getValue());
            } else if (Constraint.OUTSTOCK_SEARCH_CONDITION_BARCODE.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" materiel.barcode like '%" + entry.getValue() + "%'");
            } else if (Constraint.OUTSTOCK_SEARCH_CONDITION_NAME.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" materiel.name like '%" + entry.getValue() + "%'");
            } else if (Constraint.OUTSTOCK_SEARCH_CONDITION_FROM.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" entryDatetime >= :from");
                condition.getParam().put("from", entry.getValue());
            } else if (Constraint.OUTSTOCK_SEARCH_CONDITION_TO.equals(entry.getKey())) {
                if (!condition.getParam().isEmpty()) {
                    hql.append(" and");
                }
                hql.append(" entryDatetime < :to");
                condition.getParam().put("to", entry.getValue());
            }
        }
        if (" where".equals(hql.toString())) {
            return new ConditionModel();
        } else {
            condition.setHql(hql.toString());
            return condition;
        }
    }
}
