package com.wittymonkey.service.impl;

import com.wittymonkey.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IUserService;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserByStaffNo(String staffNo) {
		return userDao.getUserByStaffNo(staffNo);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		return (User) userDao.save(user);
	}

	@Override
	public boolean validateLoginByLoginName(String staffNo, String password) {
		if (userDao.getUserByStaffNoAndPassword(staffNo, MD5Util.encrypt(password)) == null) {
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

	public Integer getTotalByHotel(Integer hotelId){
		return userDao.getTotalByHotel(hotelId);
	}

	@Override
	public List<User> getUserByPage(Integer hotelId, Integer start, Integer total) {
		return userDao.getUserByPage(hotelId, start, total);
	}

	@Override
	public String getNextStaffNoByHotel(Integer hotelId) {
		return userDao.getNextStaffNoByHotel(hotelId);
	}

}
