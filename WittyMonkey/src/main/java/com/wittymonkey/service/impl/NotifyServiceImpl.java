package com.wittymonkey.service.impl;

import com.wittymonkey.dao.INotifyDao;
import com.wittymonkey.entity.Notify;
import com.wittymonkey.service.INotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "notifyService")
public class NotifyServiceImpl implements INotifyService {

    @Autowired
    private INotifyDao notifyDao;

    @Override
    public void save(Notify notify) {
        notifyDao.save(notify);
    }

    @Override
    public Integer getTotalSendByUser(Integer userId, Boolean inTrash, Boolean deleted) {
        return notifyDao.getTotalSendByUser(userId,inTrash,deleted);
    }

    @Override
    public List<Notify> getNotifySendByUser(Integer userId, Boolean inTrash, Boolean deleted, Integer start, Integer total) {
        return notifyDao.getNotifySendByUser(userId,inTrash,deleted, start, total);
    }

    @Override
    public Notify getNotifyById(Integer id) {
        return notifyDao.getNotifyById(id);
    }
}
