package com.wittymonkey.service;

import com.wittymonkey.entity.Notify;

import java.util.List;

public interface INotifyService {
    void save(Notify notify);

    Integer getTotalSendByUser(Integer userId, Boolean inTrash, Boolean deleted);

    List<Notify> getNotifySendByUser(Integer userId, Boolean inTrash, Boolean deleted, Integer start, Integer total);

    Notify getNotifyById(Integer id);
}
