package com.wittymonkey.dao;

import com.wittymonkey.entity.NotifyReceiver;

import java.io.Serializable;
import java.util.List;

public interface INotifyReceiverDao extends IGenericDao<NotifyReceiver, Serializable> {

    Integer getTotalByStaffNo(String staffNo, Boolean delete);

    List<NotifyReceiver> getNotifyReceiverByStaffNo(String staffNo, Boolean delete, Integer start, Integer total);
}
