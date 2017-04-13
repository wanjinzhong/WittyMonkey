package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.Role;

public interface IRoleDao extends IGenericDao<Role, Serializable>{

    Role getRoleById(Integer id);

    Integer getTotal(Integer hotelId);

    List<Role> getRolesByPage(Integer hotel, Integer start, Integer pageSize);

    Role getRoleByRoleName(Integer hotelId, String roleName);

    void deleteRole(Role role);
}
