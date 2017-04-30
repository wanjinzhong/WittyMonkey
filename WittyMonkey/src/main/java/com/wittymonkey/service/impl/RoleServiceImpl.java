package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IRoleDao;
import com.wittymonkey.entity.Role;
import com.wittymonkey.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role getRoleById(Integer id) {
        return roleDao.getRoleById(id);
    }

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
        return roleDao.getRolesByPage(hotelId, start, pageSize);
    }

    @Override
    public Role getRoleByRoleName(Integer hotelId, String roleName) {
        return roleDao.getRoleByRoleName(hotelId, roleName);
    }

    @Override
    public void deleteRole(Role role) throws SQLException {
        roleDao.deleteRole(role);
    }

}
