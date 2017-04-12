package com.wittymonkey.service;

import com.wittymonkey.entity.Role;

import java.util.List;

public interface IRoleService {

	void saveRole(Role role);

	Integer getTotal(Integer hotelId);

	List<Role> getRoleByPage(Integer hotelId, Integer start, Integer pageSize);

	Role getRoleByRoleName(Integer hotelId, String roleName);
}
