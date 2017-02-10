package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.entity.Materiel;

@Repository(value="materielDao")
public class MaterielDaoImpl2 extends GenericDaoImpl<Materiel> implements IMaterielDao{

}
