package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.INotifyReceiverDao;
import com.wittymonkey.entity.NotifyReceiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="notifyReceiverDao")
public class NotifyReveiverDaoImpl extends GenericDaoImpl<NotifyReceiver> implements INotifyReceiverDao{

    @Override
    public Integer getTotalByStaffNo(String staffNo, Boolean delete) {
        String hql = "select count(1) from NotifyReceiver where receiver.staffNo = :staffNo and isDelete = :delete";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("staffNo", staffNo);
        param.put("delete", delete);
        return countHQL(hql, param);
    }

    @Override
    public List<NotifyReceiver> getNotifyReceiverByStaffNo(String staffNo, Boolean delete, Integer start, Integer total) {
        String hql = "from NotifyReceiver where receiver.staffNo = :staffNo and isDelete = :delete order by notify.sendDate desc";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("staffNo", staffNo);
        param.put("delete", delete);
        return queryListHQL(hql, param, start, total);
    }
}
