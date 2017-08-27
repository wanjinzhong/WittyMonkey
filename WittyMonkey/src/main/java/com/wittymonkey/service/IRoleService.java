package com.wittymonkey.service;

import com.wittymonkey.entity.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleService {

    Role getRoleById(Integer id);

    void saveRole(Role role);

    Integer getTotal(Integer hotelId);

    List<Role> getRoleByPage(Integer hotelId, Integer start, Integer pageSize);

    Role getRoleByRoleName(Integer hotelId, String roleName);

    void deleteRole(Role role) throws SQLException;
}
