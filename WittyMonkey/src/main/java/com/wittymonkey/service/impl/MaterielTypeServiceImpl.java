package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IMaterielTypeDao;
import com.wittymonkey.entity.MaterielType;
import com.wittymonkey.service.IMaterielTypeService;

@Service(value="materielTypeService")
public class MaterielTypeServiceImpl implements IMaterielTypeService{

	@Autowired
	private IMaterielTypeDao materielTypeDao;
	
	@Override
	public void saveMaterielType(MaterielType materielType) {
		materielTypeDao.save(materielType);
	}

}
