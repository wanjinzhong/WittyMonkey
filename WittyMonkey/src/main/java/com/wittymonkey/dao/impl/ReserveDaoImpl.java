package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="reserveDao")
public class  ReserveDaoImpl extends GenericDaoImpl<Reserve> implements IReserveDao{

    @Override
    public Reserve getReserveById(Integer reserveId) {
        String hql = "from Reserve where id = ?";
        return queryOneHql(hql, reserveId);
    }

    @Override
    public List<Reserve> getReserveByRoomId(Integer roomId, Integer status, Integer start, Integer pageSize) {
        String hql = "from Reserve where room.id = :roomId and status = :status order by estCheckinDate";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roomId", roomId);
        param.put("status", status);
        return queryListHQL(hql, param, start, pageSize);
    }

    @Override
    public Reserve getReserveByDate(Integer roomId, Date date) {
        String hql = "from Reserve where room.id = :roomId and estCheckinDate <= :d1 and estCheckoutDate > :d2 ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roomId", roomId);
        param.put("d1", date);
        param.put("d2", date);
        return queryOneHql(hql, param);
    }

    @Override
    public List<Reserve> getAllReservesByDate(Date date) {
        String hql = "from Reserve where estCheckinDate <= :d1 and estCheckoutDate >= :d2 ";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("d1", date);
        param.put("d2", date);
        return queryListHQL(hql, param);
    }

    @Override
    public Integer getTotalByRoomIdReserved(Integer roomId, Integer status) {
        String hql = "select count(1) from Reserve where room.id = :roomId and status = :status";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roomId", roomId);
        param.put("status", status);
        return countHQL(hql, param);
    }


}
