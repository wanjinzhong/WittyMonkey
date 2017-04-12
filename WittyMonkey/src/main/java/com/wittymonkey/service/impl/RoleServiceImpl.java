package com.wittymonkey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IRoleDao;
import com.wittymonkey.entity.Role;
import com.wittymonkey.service.IRoleService;

import java.util.List;

@Service(value="roleService")
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public void saveRole(Role role) {
		roleDao.save(role);
	}

	@Override
	public Integer getTotal(Integer hotelId) {
		return roleDao.getTotal(hotelId);
	}

	@Override
	public List<Role> getRoleByPage(Integer hotelId, Integer start, Integer pageSize) {
		return roleDao.getRolesByPage(hotelId,start,pageSize);
	}

}
