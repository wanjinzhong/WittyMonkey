package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMaterielTypeDao;
import com.wittymonkey.entity.MaterielType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="materielTypeDao")
public class MaterielTypeDaoImpl extends GenericDaoImpl<MaterielType> implements IMaterielTypeDao{

    @Override
    public List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize) {
        String hql = "from MaterielType where hotel.id = :hotelId order by entryDatetime";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return queryListHQL(hql,param,start,pageSize);
    }

    @Override
    public Integer getTotalByHotelId(Integer hotelId) {
        String hql = "select count(1) from MaterielType where hotel.id = :hotelId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        return countHQL(hql,param);
    }

    @Override
    public MaterielType getMaterielTypeByName(Integer hotelId, String name) {
        String hql = "from MaterielType where hotel.id = :hotelId and name = :name";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("name", name);
        return queryOneHql(hql, param);
    }
}
