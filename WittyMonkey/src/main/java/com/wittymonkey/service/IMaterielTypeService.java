package com.wittymonkey.service;

import com.wittymonkey.entity.MaterielType;

import java.util.List;

public interface IMaterielTypeService {

	/**
	 * 保存物料类型
	 * @param materielType
	 */
	void saveMaterielType(MaterielType materielType);

	Integer getTotalByHotelId(Integer hotelId);

	List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize);

	MaterielType getMaterielTypeByName(Integer hotelId, String name);
}
