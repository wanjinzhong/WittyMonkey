package com.wittymonkey.service;

import com.wittymonkey.entity.NotifyReceiver;

import java.util.List;

public interface INotifyReceiverService {

    Integer getTotalByStaffNo(String staffNo, Boolean delete);

    List<NotifyReceiver> getNotifyReceiverByStaffNo(String staffNo, Boolean delete, Integer start, Integer total);
}
