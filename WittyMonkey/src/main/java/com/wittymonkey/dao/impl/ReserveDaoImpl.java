package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IReserveDao;
import com.wittymonkey.entity.Reserve;

import java.text.SimpleDateFormat;
import java.util.*;

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
    public Reserve getReserveByDate(Integer roomId, Integer status, Date date) {
        String hql = "from Reserve where room.id = :roomId and status = :status and estCheckinDate <= :d1 and estCheckoutDate > :d2 ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roomId", roomId);
        param.put("status", status);
        param.put("d1", simpleDateFormat.format(date));
        param.put("d2", simpleDateFormat.format(date));
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

    @Override
    public List<Reserve> getReservesViaEstCheckIn(Integer hotelId, Date from, Date to) {
        String hql = "from Reserve where room.floor.hotel.id = :hotelId and estCheckinDate >= :from and estCheckinDate <= :to  and status = 0";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("from", from);
        param.put("to", to);
        List<Reserve> list = queryListHQL(hql, param);
        return list;
    }


}
