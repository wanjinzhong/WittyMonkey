package com.wittymonkey.dao.impl;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.wittymonkey.vo.ConditionModel;
import com.wittymonkey.vo.Constraint;
import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.entity.Materiel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="materielDao")
public class MaterielDaoImpl extends GenericDaoImpl<Materiel> implements IMaterielDao{

    @Override
    public List<Materiel> getMaterielByType(Integer typeId) {
        String hql = "from Materiel where materielType.id = ?";
        return queryListHQL(hql, typeId);
    }

    @Override
    public Integer getTotal(Map<Integer, Object> condition) {
        String hql = "select count(1) from Materiel";
        ConditionModel model = assymblyCondition(condition);
        hql += model.getHql();
        return countHQL(hql, model.getParam());
    }

    @Override
    public List<Materiel> getMaterielByPage(Map<Integer, Object> condition, Integer start, Integer total) {
        String hql = "from Materiel";
        ConditionModel model = assymblyCondition(condition);
        hql += model.getHql() + " order by entryDatetime desc";
        return queryListHQL(hql, model.getParam(),start,total);
    }

    @Override
    public Materiel getMaterielByBarcode(Integer hotelId, String barcode) {
        String hql = "from Materiel where materielType.hotel.id = :hotelId and barcode = :barcode";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("barcode", barcode);
        return queryOneHql(hql, param);
    }

    @Override
    public Materiel getMaterielByName(Integer hotelId, String name) {
        String hql = "from Materiel where name = :name and materielType.hotel.id = :hotelId";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hotelId", hotelId);
        map.put("name", name);
        return queryOneHql(hql, map);
    }

    @Override
    public Materiel getMaterielById(Integer id) {
        String hql = "from Materiel where id = ?";
        return queryOneHql(hql, id);
    }

    private ConditionModel assymblyCondition(Map<Integer, Object> condition){
        ConditionModel model = new ConditionModel();
        StringBuilder builder = new StringBuilder(" where");
        for(Map.Entry<Integer, Object> entry: condition.entrySet()){
            if (Constraint.MATERIEL_SEARCH_CONDITION_HOTEL_ID.equals(entry.getKey())){
                if (!model.getParam().isEmpty()){
                    builder.append(" and");
                }
                builder.append(" materielType.hotel.id = :hotelId");
                model.getParam().put("hotelId", entry.getValue());
            } else if (Constraint.MATERIEL_SEARCH_CONDITION_TYPE_ID.equals(entry.getKey())){
                if (!model.getParam().isEmpty()){
                    builder.append(" and");
                }
                builder.append(" materielType = :typeId");
                model.getParam().put("typeId", entry.getValue());
            } else if (Constraint.MATERIEL_SEARCH_CONDITION_WARN.equals((entry.getKey()))){
                if (!model.getParam().isEmpty()){
                    builder.append(" and");
                }
                if ((Boolean)entry.getValue()){
                    builder.append(" stock < warningStock");
                } else {
                    builder.append(" stock >= warningStock");
                }
            } else if (Constraint.MATERIEL_SEARCH_CONDITION_BARCODE.equals(entry.getKey())){
                if (!model.getParam().isEmpty()){
                    builder.append(" and");
                }
                builder.append(" barcode like '%" + entry.getValue() + "%'");
            } else if (Constraint.MATERIEL_SEARCH_CONDITION_NAME.equals(entry.getKey())){
                if (!model.getParam().isEmpty()){
                    builder.append(" and");
                }
                builder.append(" name like '%" + entry.getValue() + "%'");
            }
        }
        model.setHql(builder.toString());
        if (" where".equals(builder.toString())){
            return new ConditionModel();
        } else {
            return model;
        }
    }
}
