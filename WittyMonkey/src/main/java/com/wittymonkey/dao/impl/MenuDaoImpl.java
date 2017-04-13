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
		return queryListHQL(hql, null);
	}

	@Override
	public Menu getMenuById(Integer id) {
		String hql = "from Menu where id = ?";
		return queryOneHql(hql, id);
	}

	@Override
	public List<Menu> getAllUnconfigurable() {
		String hql = "from Menu where configurable = false";
		return queryListHQL(hql,null);
	}

	@Override
	public List<Menu> getAllConfigurable() {
		String hql = "from Menu where configurable = true";
		return queryListHQL(hql,null);
	}

}
