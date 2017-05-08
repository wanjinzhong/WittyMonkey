package com.wittymonkey.dao;

import com.wittymonkey.entity.Notify;

import java.io.Serializable;
import java.util.List;

public interface INotifyDao extends IGenericDao<Notify, Serializable> {

    Integer getTotalSendByUser(Integer userId, Boolean inTrash, Boolean deleted);

    List<Notify> getNotifySendByUser(Integer userId, Boolean inTrash, Boolean deleted, Integer start, Integer total);

    Notify getNotifyById(Integer id);
}
