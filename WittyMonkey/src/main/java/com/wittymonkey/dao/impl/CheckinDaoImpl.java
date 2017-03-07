package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ICheckinDao;
import com.wittymonkey.entity.Checkin;

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
}
