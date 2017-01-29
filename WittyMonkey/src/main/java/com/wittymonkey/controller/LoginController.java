package com.wittymonkey.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.IUserService;
import com.wittymonkey.util.IDCardValidate;
import com.wittymonkey.util.SendEmail;
import com.wittymonkey.util.ValidateCodeServlet;
import com.wittymonkey.vo.IDCardInfo;

@Controller
public class LoginController {

	@Autowired
	private IHotelService hotelService;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request) {

		return "login";
	}

	@RequestMapping(value = "toRegist", method = RequestMethod.GET)
	public String toRegist(HttpServletRequest request) {
		return "regist";
	}

	/**
	 * 
	 * @param request
	 * @return JsonString: {"status", code}
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>200</td>
	 *         <td>登陆成功</td>
	 *         </tr>
	 *         <tr>
	 *         <td>400</td>
	 *         <td>没有填写用户名</td>
	 *         </tr>
	 *         <tr>
	 *         <td>410</td>
	 *         <td>没有填写密码</td>
	 *         </tr>
	 *         <tr>
	 *         <td>420</td>
	 *         <td>没有填写验证码</td>
	 *         </tr>
	 *         <tr>
	 *         <td>421</td>
	 *         <td>验证码不正确</td>
	 *         </tr>
	 *         <tr>
	 *         <td>430</td>
	 *         <td>用户名或密码不正确</td>
	 *         </tr>
	 *         </table>
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		if (loginName == null || loginName.equals("")){
			json.put("status", 400);
			return json.toJSONString();
		} else if (password == null || password.equals("")){
			json.put("status", 410);
			return json.toJSONString();
		} else if (code == null || code.trim().equals("")){
			json.put("status", 420);
			return json.toJSONString();
		} else if (!ValidateCodeServlet.validate(request, code)){
			json.put("status", 421);
			return json.toJSONString();
		} else {
			User user = new User();
			user.setLoginName(loginName);
			user.setPassword(password);
			if (!userService.validateLogin(user)){
				json.put("status", 430);
			} else {
				request.getSession().setAttribute("loginUser", user);
				json.put("status", 200);
				json.put("url", "index.do");
			}
		}
		return json.toJSONString();
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(HttpServletRequest request){
		return "index";
	}
	
	@RequestMapping(value = "getValidateCode", method = RequestMethod.GET)
	@ResponseBody
	public String getValidateCode(HttpServletRequest request) {
		String email = request.getParameter("email");
		String code = SendEmail.sendValidateCode(email);
		request.getSession().setAttribute("registCode", code);
		JSONObject json = new JSONObject();
		json.put("status", "success");
		return json.toJSONString();
	}

	@RequestMapping(value = "validateEmailCode", method = RequestMethod.GET)
	@ResponseBody
	public String validateEmailCode(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String myCode = request.getParameter("code");
		json.put("status", validateInpEmailCode(request, myCode));
		return json.toJSONString();

	}
	
	@RequestMapping(value = "validatePicCode", method = RequestMethod.GET)
	@ResponseBody
	public String validatePicCode(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String myCode = request.getParameter("code");
		json.put("status", validateInpPicCode(request, myCode));
		return json.toJSONString();
	}
	
	
	/**
	 * 注册
	 * 
	 * @param request
	 * @return JsonString: {"status", code}
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>200</td>
	 *         <td>注册成功</td>
	 *         </tr>
	 *         <tr>
	 *         <td>400</td>
	 *         <td>没有填写用户名</td>
	 *         </tr>
	 *         <tr>
	 *         <td>401</td>
	 *         <td>用户名已存在</td>
	 *         </tr>
	 *         <tr>
	 *         <td>410</td>
	 *         <td>密码小于6位</td>
	 *         </tr>
	 *         <tr>
	 *         <td>411</td>
	 *         <td>两次密码不一致</td>
	 *         </tr>
	 *         <tr>
	 *         <td>420</td>
	 *         <td>邮箱格式不正确</td>
	 *         </tr>
	 *         <tr>
	 *         <td>430</td>
	 *         <td>没有获取验证码</td>
	 *         </tr>
	 *         <tr>
	 *         <td>431</td>
	 *         <td>验证码错误</td>
	 *         </tr>
	 *         </table>
	 */
	@RequestMapping(value = "regist.do", method = RequestMethod.POST)
	@ResponseBody
	public String regist(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		String myCode = request.getParameter("code");
		if (loginName == null || loginName.trim().equals("")) {
			json.put("status", 400);
			return json.toJSONString();
		} else if (isLoginNameExist(loginName)) {
			json.put("status", 401);
			return json.toJSONString();
		} else if (password == null || password.trim().equals("") || password.length() < 6) {
			json.put("status", 410);
			return json.toJSONString();
		} else if (!password.equals(repassword)) {
			json.put("status", 411);
			return json.toJSONString();
		} else if (!validateEmail(email)) {
			json.put("status", 420);
			return json.toJSONString();
		} else if (validateInpEmailCode(request, myCode) == 400) {
			json.put("status", 430);
			return json.toJSONString();
		} else if (validateInpEmailCode(request, myCode) == 401) {
			json.put("status", 431);
			return json.toJSONString();
		} else {
			User user = new User();
			user.setLoginName(loginName);
			user.setPassword(password);
			user.setEmail(email);
			userService.addUser(user);
			json.put("status", 200);
			return json.toJSONString();
		}
	}

	/**
	 * 验证用户名是否存在
	 * 
	 * @param request
	 * @return {"status": code}
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>200</td>
	 *         <td>用户存在</td>
	 *         </tr>
	 *         <tr>
	 *         <td>201</td>
	 *         <td>用户不存在</td>
	 *         </tr>
	 *         </table>
	 */
	@RequestMapping(value = "validateLoginName", method = RequestMethod.GET)
	@ResponseBody
	public String validateLoginName(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String loginName = request.getParameter("loginName");
		if (isLoginNameExist(loginName)) {
			json.put("status", 200);
		} else {
			json.put("status", 201);
		}
		return json.toJSONString();
	}

	/**
	 * 用户名是否存在
	 * 
	 * @param loginName
	 * @return
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>true</td>
	 *         <td>用户存在</td>
	 *         </tr>
	 *         <tr>
	 *         <td>false</td>
	 *         <td>用户不存在</td>
	 *         </tr>
	 *         </table>
	 */
	public boolean isLoginNameExist(String loginName) {
		if (userService.getUserByLoginName(loginName) == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 验证邮箱格式是否正确
	 * 
	 * @param email
	 * @return
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>true</td>
	 *         <td>邮箱格式正确</td>
	 *         </tr>
	 *         <tr>
	 *         <td>false</td>
	 *         <td>邮箱格式不正确</td>
	 *         </tr>
	 *         </table>
	 */
	public boolean validateEmail(String email) {
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		return regex.matcher(email).matches();
	}

	/**
	 * 验证 邮件验证码
	 * 
	 * @param request
	 * @return
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>200</td>
	 *         <td>验证通过</td>
	 *         </tr>
	 *         <tr>
	 *         <td>400</td>
	 *         <td>没有获取验证码</td>
	 *         </tr>
	 *         <tr>
	 *         <td>401</td>
	 *         <td>验证码错误</td>
	 *         </tr>
	 *         </table>
	 */
	public int validateInpEmailCode(HttpServletRequest request, String myCode) {
		String realCode = (String) request.getSession().getAttribute("registCode");
		if (realCode == null) {
			return 400;
		} else if (myCode.equals(realCode)) {
			return 200;
		} else {
			return 401;
		}
	}
	/**
	 * 验证 图片验证码
	 * 
	 * @param request
	 * @return
	 *         <table border="1" cellspacing="0">
	 *         <tr>
	 *         <th>代码</th>
	 *         <th>说明</th>
	 *         </tr>
	 *         <tr>
	 *         <td>200</td>
	 *         <td>验证通过</td>
	 *         </tr>
	 *         <tr>
	 *         <td>400</td>
	 *         <td>没有输入验证码</td>
	 *         </tr>
	 *         <tr>
	 *         <td>401</td>
	 *         <td>验证码错误</td>
	 *         </tr>
	 *         </table>
	 */
	public int validateInpPicCode(HttpServletRequest request, String myCode){
		String realCode = (String) request.getSession().getAttribute(ValidateCodeServlet.VALIDATE_CODE);
		if (myCode == null) {
			return 400;
		} else if (myCode.equals(realCode)) {
			return 200;
		} else {
			return 401;
		}
	}
}
