package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMaterielTypeDao;
import com.wittymonkey.entity.MaterielType;

@Repository(value="materielTypeDao")
public class MaterielTypeDaoImpl extends GenericDaoImpl<MaterielType> implements IMaterielTypeDao{

}
