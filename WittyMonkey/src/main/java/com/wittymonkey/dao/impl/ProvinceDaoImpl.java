package com.wittymonkey.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IProvinceDao;
import com.wittymonkey.entity.Province;

@Repository(value="provinceDao")
public class ProvinceDaoImpl extends GenericDaoImpl<Province> implements IProvinceDao{

	@Override
	public List<Province> getAll() {
		String hql = "from Province order by code asc";
		return queryHQL(hql, null);
	}

}
