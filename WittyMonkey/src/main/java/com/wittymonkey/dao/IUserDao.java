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
	 * @param staffNo
	 * @return
	 */
	User getUserByStaffNo(String staffNo);
	
	/**
	 * 根据登陆名和密码获取用户（用于登陆验证）
	 * @param
	 * @return
	 */
	User getUserByStaffNoAndPassword(String staffNo, String password);

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
	List<User> getUserByPage(Integer hotel, Integer type, Integer start, Integer total);

	/**
	 * 获取酒店内所有员工人数
	 * @param hotel
	 * @return
	 */
	Integer getTotalByHotel(Integer hotel, Integer type);

	/**
	 * 根据酒店id生成下一个员工的员工号（调用存储过程）
	 * @param hotelId
	 * @return
	 */
	String getNextStaffNoByHotel(Integer hotelId);
}
