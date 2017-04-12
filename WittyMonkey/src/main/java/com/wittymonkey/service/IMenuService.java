package com.wittymonkey.service;

import java.util.List;

import com.wittymonkey.entity.Menu;

public interface IMenuService {

	/**
	 * 获取所有菜单
	 *
	 * @return
	 */
	List<Menu> getAll();
}