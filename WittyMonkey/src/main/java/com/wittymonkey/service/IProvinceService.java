package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.Province;

public interface IProvinceService {

	/**
	 * 获取所有省
	 * 
	 * @return
	 */
	public List<Province> getAll();
}
