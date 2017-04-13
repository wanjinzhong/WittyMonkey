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

	Menu getMenuById(Integer id);

	/**
	 * 获取所有不可配置的菜单
	 * @return
	 */
	List<Menu> getAllUnconfigurable();

	/**
	 * 获取所有可配置的菜单
	 * @return
	 */
	List<Menu> getAllConfigurable();
}