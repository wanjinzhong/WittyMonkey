package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="checkinDao")
public class CheckinDaoImpl extends GenericDaoImpl<Checkin> implements ICheckinDao{

    @Override
    public Checkin getCheckinByRoomUncomplete(Integer roomId) {
        String hql = "from Checkin where room.id = ? and actCheckoutDate = null";
        return queryOneHql(hql,roomId);
    }

    @Override
    public Checkin getCheckinById(Integer id) {
        String hql = "from Checkin where id = ?";
        return queryOneHql(hql, id);
    }

    @Override
    public List<Checkin> getCheckinViaEstCheckoutDate(Integer hotelId, Date start, Date to) {
        String hql = "from Checkin where room.floor.hotel.id = :hotelId and estCheckoutDate >= :from and estCheckoutDate <= :to and actCheckoutDate is null";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("from", start);
        param.put("to", to);
        return queryListHQL(hql, param);
    }
}
