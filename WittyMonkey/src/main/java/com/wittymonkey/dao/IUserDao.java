package com.wittymonkey.dao;

import java.io.Serializable;

import com.wittymonkey.entity.User;

public interface IUserDao extends IGenericDao<User, Serializable>{
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 根据登陆名获取用户
	 * @param loginName
	 * @return
	 */
	User getUserByLoginName(String loginName);
	
	/**
	 * 根据登陆名和密码获取用户（用于登陆验证）
	 * @param
	 * @return
	 */
	User getUserByLoginNameAndPassword(String loginName, String password);

	/**
	 * 根据邮箱和密码获取用户（用于登陆验证）
	 * @param
	 * @return
	 */
	User getUserByEmailAndPassword(String email,String password);
	
	void saveUser(User user);

	/**
	 * 根据邮箱查找用户
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);


}
