package com.wittymonkey.service.impl;

import java.util.List;

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

}
