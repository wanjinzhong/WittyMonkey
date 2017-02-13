package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IProvinceDao;
import com.wittymonkey.entity.Province;

@Repository(value="provinceDao")
public class ProvinceDaoImpl extends GenericDaoImpl<Province> implements IProvinceDao{

	@Override
	public List<Province> getAll() {
		String hql = "from Province order by code asc";
		return queryListHQL(hql, null);
	}

	@Override
	public Province getProvinceByCode(Integer code) {
		String hql = "from Province where code = :code";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		return queryOneHql(hql,param);
	}

}
