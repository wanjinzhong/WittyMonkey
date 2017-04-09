package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

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

	/**
	 * 分页获取酒店内所有员工
	 * @param hotel
	 * @param start
	 * @param total
	 * @return
	 */
	List<User> getUserByPage(Integer hotel, Integer start, Integer total);

	/**
	 * 获取酒店内所有员工人数
	 * @param hotel
	 * @return
	 */
	Integer getTotalByHotel(Integer hotel);
}
