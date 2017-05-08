package com.wittymonkey.service.impl;

import com.wittymonkey.dao.INotifyReceiverDao;
import com.wittymonkey.entity.NotifyReceiver;
import com.wittymonkey.service.INotifyReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "notifyReceiverService")
public class NotifyReceiverServiceImpl implements INotifyReceiverService {

    @Autowired
    private INotifyReceiverDao notifyReceiverDao;

    @Override
    public Integer getTotalByStaffNo(String staffNo, Boolean delete) {
        return notifyReceiverDao.getTotalByStaffNo(staffNo, delete);
    }

    @Override
    public List<NotifyReceiver> getNotifyReceiverByStaffNo(String staffNo, Boolean delete, Integer start, Integer total) {
        return notifyReceiverDao.getNotifyReceiverByStaffNo(staffNo, delete, start, total);
    }
}
