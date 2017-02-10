package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.INotifyReceiverDao;
import com.wittymonkey.entity.NotifyReceiver;

@Repository(value="notifyReceiverDao")
public class NotifyReveiverDaoImpl extends GenericDaoImpl<NotifyReceiver> implements INotifyReceiverDao{

}
