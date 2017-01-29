package com.wittymonkey.service;

import com.wittymonkey.entity.User;

public interface IUserService {
	/**
	 * 根据登陆名查询用户
	 * @param loginName
	 * @return
	 */
	public User getUserByLoginName(String loginName);
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 验证用户名和密码
	 * @param user
	 * @return
	 * <table border="1" cellspacing="0">
	 * <tr><th>代码</th><th>说明</th></tr>
	 * <tr><td>true</td><td>验证通过</td></tr>
	 * <tr><td>false</td><td>验证失败</td></tr>
	 * </table>
	 */
	public boolean validateLogin(User user);
}
