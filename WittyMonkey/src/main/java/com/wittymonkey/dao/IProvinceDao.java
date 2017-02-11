package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.Province;

public interface IProvinceDao extends IGenericDao<Province, Serializable>{

	/**
	 * 获取所有省
	 * @return
	 */
	public List<Province> getAll();
}
