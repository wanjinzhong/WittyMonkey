package com.wittymonkey.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMenuDao;
import com.wittymonkey.entity.Menu;

@Repository(value="menuDao")
public class MenuDaoImpl extends GenericDaoImpl<Menu> implements IMenuDao {

	@Override
	public List<Menu> getAll() {
		String hql = "from Menu";
		return queryHQL(hql, null);
	}

}
