package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.INotifyDao;
import com.wittymonkey.entity.Notify;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="notifyDao")
public class NotifyDaoImpl extends GenericDaoImpl<Notify> implements INotifyDao{

    @Override
    public Integer getTotalSendByUser(Integer userId, Boolean inTrash, Boolean deleted) {
        String hql = "select count(1) from Notify where sender.id = ?";
        return countHQL(hql, userId);
    }

    @Override
    public List<Notify> getNotifySendByUser(Integer userId, Boolean inTrash, Boolean deleted, Integer start, Integer total) {
        String hql = "from Notify where sender.id = ? order by sendDate desc";
        return queryListHQL(hql, userId, start, total);
    }

    @Override
    public Notify getNotifyById(Integer id) {
        String hql = "from Notify where id = ?";
        return queryOneHql(hql, id);
    }
}
