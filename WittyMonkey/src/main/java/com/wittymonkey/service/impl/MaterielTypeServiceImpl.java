package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IMaterielTypeDao;
import com.wittymonkey.entity.MaterielType;
import com.wittymonkey.service.IMaterielTypeService;

import java.util.List;

@Service(value="materielTypeService")
public class MaterielTypeServiceImpl implements IMaterielTypeService{

	@Autowired
	private IMaterielTypeDao materielTypeDao;
	
	@Override
	public void saveMaterielType(MaterielType materielType) {
		materielTypeDao.save(materielType);
	}

	@Override
	public Integer getTotalByHotelId(Integer hotelId) {
		return materielTypeDao.getTotalByHotelId(hotelId);
	}

	@Override
	public List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize){
		return materielTypeDao.getMaterielTypeByHotelId(hotelId, start, pageSize);
	}

	@Override
	public MaterielType getMaterielTypeByName(Integer hotelId, String name) {
		return materielTypeDao.getMaterielTypeByName(hotelId,name);
	}
}
