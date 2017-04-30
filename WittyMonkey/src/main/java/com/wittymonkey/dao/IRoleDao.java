package com.wittymonkey.dao;

import com.wittymonkey.entity.Role;

import java.io.Serializable;
import java.util.List;

public interface IRoleDao extends IGenericDao<Role, Serializable> {

    Role getRoleById(Integer id);

    Integer getTotal(Integer hotelId);

    List<Role> getRolesByPage(Integer hotel, Integer start, Integer pageSize);

    Role getRoleByRoleName(Integer hotelId, String roleName);

    void deleteRole(Role role);
}
