package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoleDao;
import com.wittymonkey.entity.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements IRoleDao{

    @Override
    public Integer getTotal(Integer hotelId) {
        String hql = "select count(1) from Role where hotel.id = ?";
        return countHQL(hql,hotelId);
    }

    @Override
    public List<Role> getRolesByPage(Integer hotel, Integer start, Integer pageSize) {
        String hql = "from Role where hotel.id = :hotelId order by id";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotel);
        return queryListHQL(hql,param,start,pageSize);
    }
}
