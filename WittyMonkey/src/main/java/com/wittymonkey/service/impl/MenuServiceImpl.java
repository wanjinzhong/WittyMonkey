package com.wittymonkey.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.wittymonkey.controller.IndexController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IMenuDao;
import com.wittymonkey.entity.Menu;
import com.wittymonkey.service.IMenuService;

@Service(value="menuService")
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IMenuDao menuDao;
	
	@Override
	public List<Menu> getAll() {
		return menuDao.getAll();
	}

	@Override
	public Menu getMenuById(Integer id) {
		return menuDao.getMenuById(id);
	}

	@Override
	public List<Menu> getAllUnconfigurable() {
		return menuDao.getAllUnconfigurable();
	}

	@Override
	public List<Menu> getAllConfigurable() {
		return menuDao.getAllConfigurable();
	}
}
