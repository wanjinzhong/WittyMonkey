package com.wittymonkey.dao;

import com.wittymonkey.entity.Setting;

import java.io.Serializable;

public interface ISettingDao extends IGenericDao<Setting, Serializable> {

    Setting getSettingByUser(Integer userId);
}
