package com.wittymonkey.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.User;

@Repository(value = "userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {

	@Override
	public User getUserByLoginName(String loginName) {
		String hql = "from User where loginName = :loginName";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", loginName);
		List<User> users = queryHQL(hql, param);
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getUserByLoginNameAndPassword(User user) {
		String hql = "from User where loginName = :loginName and password = :password";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", user.getLoginName());
		param.put("password", user.getPassword());
		List<User> users = queryHQL(hql, param);
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public void saveUser(User user) {
		save(user);
		getCurrentSession().flush();
	}

	@Override
	public User getUserById(Integer id) {
		String hql = "from User where id = :id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id",id);
		List<User> users = queryHQL(hql, param); 
		if (users == null || users.isEmpty()){
			return null;
		} else {
			return users.get(0);
		}
	}
	
	

}
