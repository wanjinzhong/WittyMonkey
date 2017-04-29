package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.IReimburseDao;
import com.wittymonkey.entity.Reimburse;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="reimburseDao")
public class ReimburseDaoImpl extends GenericDaoImpl<Reimburse> implements IReimburseDao{

    @Override
    public Reimburse getReimburseById(Integer id) {
        String hql = "from Reimburse where id = ?";
        return queryOneHql(hql, id);
    }

    @Override
    public Integer getTotal(Integer hotelId, Integer status, Date from, Date to) {
        StringBuffer hql = new StringBuffer("select count(1) from Reimburse where hotel.id = :hotelId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (status != null && status > 0){
            hql.append("and status = :status ");
            param.put("status", status);
        }
        if(from != null){
            hql.append("and applyDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append("and applyDatetime < :to ");
            param.put("to", to);
        }
        param.put("hotelId", hotelId);
        return countHQL(hql.toString(), param);
    }

    @Override
    public List<Reimburse> getReimburseByPage(Integer hotelId, Integer status, Date from, Date to, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from Reimburse where hotel.id = :hotelId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (status != null && status > 0){
            hql.append("and status = :status ");
            param.put("status", status);
        }
        if(from != null){
            hql.append("and applyDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append("and applyDatetime < :to ");
            param.put("to", to);
        }
        hql.append("order by applyDatetime desc");
        param.put("hotelId", hotelId);
        return queryListHQL(hql.toString(), param, start, total);
    }

    @Override
    public Integer getTotalByUser(Integer userId, Integer status, Date from, Date to) {
        StringBuffer hql = new StringBuffer("select count(1) from Reimburse where applyUser.id = :userId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (status != null && status > 0){
            hql.append("and status = :status ");
            param.put("status", status);
        }
        if(from != null){
            hql.append("and applyDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append("and applyDatetime < :to ");
            param.put("to", to);
        }
        param.put("userId", userId);
        return countHQL(hql.toString(), param);
    }

    @Override
    public List<Reimburse> getReimburseByUser(Integer userId, Integer status, Date from, Date to, Integer start, Integer total) {
        StringBuffer hql = new StringBuffer("from Reimburse where applyUser.id = :userId ");
        Map<String, Object> param = new HashMap<String, Object>();
        if (status != null && status > 0){
            hql.append("and status = :status ");
            param.put("status", status);
        }
        if(from != null){
            hql.append("and applyDatetime >= :from ");
            param.put("from", from);
        }
        if (to != null){
            hql.append("and applyDatetime < :to ");
            param.put("to", to);
        }
        hql.append("order by applyDatetime desc");
        param.put("userId", userId);
        return queryListHQL(hql.toString(), param, start, total);
    }
}
