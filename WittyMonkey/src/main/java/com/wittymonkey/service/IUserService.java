package com.wittymonkey.service;

import com.wittymonkey.entity.User;

import java.util.List;

public interface IUserService {
	
	/**
	 * 根据id获取用户
	 * @return
	 */
	User getUserById(Integer id);
	/**
	 * 根据登陆名查询用户
	 * @param StaffNo
	 * @return
	 */
	User getUserByStaffNo(String StaffNo);

	/**
	 * 根据邮箱查找用户
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);

	/**
	 * 添加用户
	 * @param user
	 */
	User saveUser(User user);
	
	/**
	 * 验证用户名和密码
	 * @param
	 * @return
	 * <table border="1" cellspacing="0">
	 * <tr><th>代码</th><th>说明</th></tr>
	 * <tr><td>true</td><td>验证通过</td></tr>
	 * <tr><td>false</td><td>验证失败</td></tr>
	 * </table>
	 */
	boolean validateLoginByLoginName(String email, String password);

	/**
	 * 验证邮箱和密码
	 * @param
	 * @return
	 * <table border="1" cellspacing="0">
	 * <tr><th>代码</th><th>说明</th></tr>
	 * <tr><td>true</td><td>验证通过</td></tr>
	 * <tr><td>false</td><td>验证失败</td></tr>
	 * </table>
	 */
	boolean validateLoginByEmail(String email, String password);

	/**
	 * 获取酒店内员工总数
	 * @param hotelId
	 * @return
	 */
	Integer getTotalByHotel(Integer hotelId, Integer type);

	List<User> getUserByPage(Integer hotelId, Integer type, Integer start, Integer total);

	String getNextStaffNoByHotel(final Integer hotelId);
}
