package com.wittymonkey.dao;

import com.wittymonkey.entity.Menu;

import java.io.Serializable;
import java.util.List;

public interface IMenuDao extends IGenericDao<Menu, Serializable> {

    /**
     * 查找所有菜单
     *
     * @return
     */
    List<Menu> getAll();

    Menu getMenuById(Integer id);

    List<Menu> getAllUnconfigurable();

    List<Menu> getAllConfigurable();

}
