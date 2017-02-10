package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IRoleDao;
import com.wittymonkey.entity.Role;

@Repository(value="roleDao")
public class RoleDaoImpl extends GenericDaoImpl<Role> implements IRoleDao{

}
