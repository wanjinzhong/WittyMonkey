package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.INotifyDao;
import com.wittymonkey.entity.Notify;

@Repository(value="notifyDao")
public class NotifyDaoImpl extends GenericDaoImpl<Notify> implements INotifyDao{

}
