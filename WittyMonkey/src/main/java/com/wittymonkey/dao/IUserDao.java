package com.wittymonkey.dao;

import java.io.Serializable;

import com.wittymonkey.entity.User;

public interface IUserDao extends IGenericDao<User, Serializable>{
	/**
	 * 根据登陆名获取用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);
	
	/**
	 * 根据登陆名和密码获取用户（用于登陆验证）
	 * @param user
	 * @return
	 */
	public User getUserByLoginNameAndPassword(User user);
}
