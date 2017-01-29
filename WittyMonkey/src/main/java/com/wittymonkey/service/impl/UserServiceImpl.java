package com.wittymonkey.service.impl;

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
	public void addUser(User user) {
		userDao.save(user);
	}

	@Override
	public boolean validateLogin(User user) {
		if (userDao.getUserByLoginNameAndPassword(user) == null) {
			return false;
		} else {
			return true;
		}
	}

}
