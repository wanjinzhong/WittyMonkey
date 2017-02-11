package com.wittymonkey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IProvinceDao;
import com.wittymonkey.entity.Province;
import com.wittymonkey.service.IProvinceService;

@Service(value="provinceService")
public class ProvinceServiceImpl implements IProvinceService{

	@Autowired
	private IProvinceDao provinceDao;
	
	@Override
	public List<Province> getAll() {
		// TODO Auto-generated method stub
		return provinceDao.getAll();
	}

}
