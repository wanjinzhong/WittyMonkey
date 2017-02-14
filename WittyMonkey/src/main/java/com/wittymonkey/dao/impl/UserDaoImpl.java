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
		List<User> users = queryListHQL(hql, param);
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User getUserByLoginNameAndPassword(String loginName, String password) {
		String hql = "from User where loginName = :loginName and password = :password";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loginName", loginName);
		param.put("password", password);
		return queryOneHql(hql,param);
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		String hql = "from User where email = :email and password = :password";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);
		param.put("password", password);
		return queryOneHql(hql,param);
	}

	@Override
	public void saveUser(User user) {
		save(user);
		getCurrentSession().flush();
	}

	@Override
	public User getUserByEmail(String email) {
		String hql = "from User where email = :email";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("email", email.toLowerCase());
		return queryOneHql(hql,param);
	}

	@Override
	public User getUserById(Integer id) {
		String hql = "from User where id = :id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id",id);
		List<User> users = queryListHQL(hql, param);
		if (users == null || users.isEmpty()){
			return null;
		} else {
			return users.get(0);
		}
	}
	
	

}
