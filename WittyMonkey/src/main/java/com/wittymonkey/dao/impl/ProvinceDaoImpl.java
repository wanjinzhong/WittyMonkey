package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IProvinceDao;
import com.wittymonkey.entity.Province;

@Repository(value="provinceDao")
public class ProvinceDaoImpl extends GenericDaoImpl<Province> implements IProvinceDao{

}
