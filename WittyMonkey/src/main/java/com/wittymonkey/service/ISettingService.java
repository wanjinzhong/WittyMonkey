package com.wittymonkey.service;

import com.wittymonkey.entity.Setting;

public interface ISettingService {

	void saveSetting(Setting setting);

	Setting getSettingByUser(Integer userId);
}
