package com.wittymonkey.service.impl;

import com.wittymonkey.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IUserService;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserByLoginName(String loginName) {
		return userDao.getUserByLoginName(loginName);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public boolean validateLoginByLoginName(String loginName, String password) {
		if (userDao.getUserByLoginNameAndPassword(loginName, MD5Util.encrypt(password)) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean validateLoginByEmail(String email, String password) {
		if (userDao.getUserByEmailAndPassword(email, MD5Util.encrypt(password)) == null){
			return false;
		}
		return true;
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}

}
