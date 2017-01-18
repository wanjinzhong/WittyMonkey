package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IAreaDao;
import com.wittymonkey.entity.Area;
import com.wittymonkey.service.IAreaService;

@Service(value="areaService")
public class AreaServiceImpl implements IAreaService{

	@Autowired
	private IAreaDao areaDao;
	@Override
	public Area getAreaByCode(String code) {
		return areaDao.getAreaByCode(code);
	}

}
