package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.ISettingDao;
import com.wittymonkey.entity.Setting;
import com.wittymonkey.service.ISettingService;

@Service(value="settingService")
public class SettingServiceImpl implements ISettingService {

	@Autowired
	private ISettingDao settingDao;

	@Override
	public void saveSetting(Setting setting) {
		settingDao.save(setting);
	}

	@Override
	public Setting getSettingByUser(Integer userId) {
		return settingDao.getSettingByUser(userId);
	}

}
