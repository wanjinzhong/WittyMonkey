package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISettingDao;
import com.wittymonkey.entity.Setting;

@Repository(value="settingDao")
public class SettingDaoImpl extends GenericDaoImpl<Setting> implements ISettingDao{

    @Override
    public Setting getSettingByUser(Integer userId) {
        String hql = "from Setting where user.id = ?";
        return queryOneHql(hql, userId);
    }
}
