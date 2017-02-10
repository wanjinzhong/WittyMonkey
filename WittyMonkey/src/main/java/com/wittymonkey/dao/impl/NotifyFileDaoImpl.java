package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.INotifyFileDao;
import com.wittymonkey.entity.NotifyFile;

@Repository(value="notifyFileDao")
public class NotifyFileDaoImpl extends GenericDaoImpl<NotifyFile> implements INotifyFileDao{

}
