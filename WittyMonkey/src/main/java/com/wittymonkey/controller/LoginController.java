package com.wittymonkey.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.wittymonkey.entity.*;
import com.wittymonkey.util.*;
import com.wittymonkey.vo.SimplePlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.service.IAreaService;
import com.wittymonkey.service.ICityService;
import com.wittymonkey.service.IHotelService;
import com.wittymonkey.service.ILeaveTypeService;
import com.wittymonkey.service.IMaterielTypeService;
import com.wittymonkey.service.IMenuService;
import com.wittymonkey.service.IProvinceService;
import com.wittymonkey.service.IRoleService;
import com.wittymonkey.service.ISettingService;
import com.wittymonkey.service.IUserService;

@Controller
public class LoginController {

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISettingService settingService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMaterielTypeService materielTypeService;

    @Autowired
    private ILeaveTypeService leaveTypeService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IAreaService areaService;

    @RequestMapping(value = "toLogin", method = RequestMethod.GET)
    public String toLogin(HttpServletRequest request) {

        return "login";
    }

    @RequestMapping(value = "toRegist", method = RequestMethod.GET)
    public String toRegist(HttpServletRequest request) {
        return toRegistHotel(request);
    }

    @RequestMapping(value = "toRegistHotel", method = RequestMethod.GET)
    public String toRegistHotel(HttpServletRequest request) {
        List<Province> provinces = provinceService.getAll();
        String provinceCode = (String) request.getSession().getAttribute("registHotelProvinceCode");
        String cityCode = (String) request.getSession().getAttribute("registHotelCityCode");
        List<City> cities;
        List<Area> areas;
        if (provinceCode != null) {
            cities = cityService.getAllByProvince(Integer.parseInt(provinceCode));
        } else {
            cities = cityService.getAllByProvince(provinces.get(0));
        }
        if (cityCode != null) {
            areas = areaService.getAllByCity(Integer.parseInt(cityCode));
        } else {
            areas = areaService.getAllByCity(cities.get(0));
        }
        request.getSession().setAttribute("provinces", provinces);
        request.getSession().setAttribute("cities", cities);
        request.getSession().setAttribute("areas", areas);
        return "regist_hotel";
    }

    @RequestMapping(value = "toRegistUser", method = RequestMethod.POST)
    public String toRegistUser(HttpServletRequest request) {
        String hotelName = request.getParameter("hotelName");
        String legalName = request.getParameter("legalName");
        String legalIdCard = request.getParameter("legalIdCard");
        String licenseNo = request.getParameter("licenseNo");
        String provinceCode = request.getParameter("provinceCode");
        String cityCode = request.getParameter("cityCode");
        String areaCode = request.getParameter("areaCode");
        String placeDetail = request.getParameter("placeDetail");
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setLegalName(legalName);
        hotel.setLegalIdCard(legalIdCard);
        hotel.setLicenseNo(licenseNo);
        if (areaCode == null) {
            if (cityCode == null) {
                hotel.setPlaceCode(Integer.parseInt(provinceCode));
            } else {
                hotel.setPlaceCode(Integer.parseInt(cityCode));
            }
        } else {
            hotel.setPlaceCode(Integer.parseInt(areaCode));
        }
        hotel.setPlaceDetail(placeDetail);
        request.getSession().setAttribute("registHotel", hotel);
        request.getSession().setAttribute("registHotelProvinceCode", provinceCode);
        request.getSession().setAttribute("registHotelCityCode", cityCode);
        request.getSession().setAttribute("registHotelAreaCode", areaCode);
        return "regist_user";
    }

    @RequestMapping(value = "toPrev", method = RequestMethod.POST)
    public String toPrev(HttpServletRequest request) {
        String loginName = request.getParameter("loginName");
        String realName = request.getParameter("realName");
        String idCard = request.getParameter("idCard");
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        User user = new User();
        user.setLoginName(loginName);
        user.setRealName(realName);
        user.setIdCardNo(idCard);
        user.setEmail(email);
        request.getSession().setAttribute("registUser", user);
        request.getSession().setAttribute("registCode", code);
        return "regist_hotel";
    }

    /**
     * 验证身份证号
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>验证通过</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>验证失败</td>
     * </tr>
     */
    @RequestMapping(value = "validateIdCard", method = RequestMethod.GET)
    @ResponseBody
    public String validateIdCard(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String idCard = request.getParameter("idCard");
        if (IDCardValidate.validate(idCard)) {
            json.put("status", 200);
        } else {
            json.put("status", 400);
        }
        return json.toJSONString();
    }

    /**
     * @param request
     * @return JsonString: {"status", code}
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>登陆成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有填写用户名</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>没有填写密码</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>没有填写验证码</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>验证码不正确</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>用户名或密码不正确</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        if (loginName == null || loginName.equals("")) {
            json.put("status", 400);
            return json.toJSONString();
        } else if (password == null || password.equals("")) {
            json.put("status", 410);
            return json.toJSONString();
        } else if (code == null || code.trim().equals("")) {
            json.put("status", 420);
            return json.toJSONString();
        } else if (!ValidateCodeServlet.validate(request, code)) {
            json.put("status", 421);
            return json.toJSONString();
        } else {
            User user = new User();
            user.setLoginName(loginName);
            user.setPassword(password);
            if (!userService.validateLoginByLoginName(loginName, password) &&
                    !userService.validateLoginByEmail(loginName, password)) {
                json.put("status", 430);
            } else {
                User loginUser = userService.getUserByLoginName(loginName);
                if (loginUser == null){
                    loginUser = userService.getUserByEmail(loginName);
                }
                request.getSession().setAttribute("loginUser", loginUser);
                request.getSession().setAttribute("hotel", loginUser.getHotel());
                json.put("status", 200);
                json.put("url", "index.do");
            }
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "getIndexInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getUserInfo(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Hotel hotel = (Hotel) request.getSession().getAttribute("hotel");
        jsonObject.put("realName", user.getRealName());
        List<Role> roles = user.getRoles();
        List<String> rolesName = new ArrayList<String>();
        for(Role role : roles){
            rolesName.add(role.getName());
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(rolesName);
        jsonObject.put("roles", rolesName);
        jsonObject.put("hotelName",hotel.getName());
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        // 测试数据
        User user = userService.getUserByLoginName("lyf");
        request.getSession().setAttribute("loginUser",user);
        request.getSession().setAttribute("hotel",user.getHotel());
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
        json.put("status", validateInpEmailCode(request));
        return json.toJSONString();

    }

    @RequestMapping(value = "validatePicCode", method = RequestMethod.GET)
    @ResponseBody
    public String validatePicCode(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String myCode = request.getParameter("code");
        json.put("status", validateInpPicCode(request, myCode));
        return json.toJSONString();
    }

    /**
     * 验证营业执照编号或身份证号前6位并返回地址
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>查询成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>长度不足</td>
     * </tr>
     */
    @RequestMapping(value = "validatePlace", method = RequestMethod.GET)
    @ResponseBody
    public String validatePlace(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String code = request.getParameter("code");
        if (code == null || code.equals("") || code.length() < 6) {
            json.put("status", 400);
        } else {
            String provinceCode = code.substring(0, 2) + "0000";
            String cityCode = code.substring(0, 4) + "00";
            String areaCode = code.substring(0, 6);
            List<City> cities;
            List<Area> areas;
            cities = cityService.getAllByProvince(Integer.parseInt(provinceCode));
            areas = areaService.getAllByCity(Integer.parseInt(cityCode));
            List<SimplePlace> simpleCities = new ArrayList<SimplePlace>();
            List<SimplePlace> simpleAreas = new ArrayList<SimplePlace>();
            for (int i = 0; i < cities.size(); i++) {
                SimplePlace simpleCity = new SimplePlace();
                simpleCity.setCode(cities.get(i).getCode());
                simpleCity.setName(cities.get(i).getName());
                simpleCities.add(simpleCity);
            }
            for (int i = 0; i < areas.size(); i++) {
                SimplePlace simpleArea = new SimplePlace();
                simpleArea.setCode(areas.get(i).getCode());
                simpleArea.setName(areas.get(i).getName());
                simpleAreas.add(simpleArea);
            }
            json.put("provinceCode", provinceCode);
            json.put("cityCode", cityCode);
            json.put("areaCode", areaCode);
            json.put("cities", simpleCities);
            json.put("areas", simpleAreas);
            json.put("status", 200);
        }

        return json.toJSONString();
    }

    /**
     * 注册
     *
     * @param request
     * @return JsonString: {"status", code}
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>注册成功</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有填写用户名</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>用户名已存在</td>
     * </tr>
     * <tr>
     * <td>410</td>
     * <td>密码小于6位</td>
     * </tr>
     * <tr>
     * <td>411</td>
     * <td>两次密码不一致</td>
     * </tr>
     * <tr>
     * <td>420</td>
     * <td>邮箱格式不正确</td>
     * </tr>
     * <tr>
     * <td>421</td>
     * <td>邮箱已被注册</td>
     * </tr>
     * <tr>
     * <td>430</td>
     * <td>没有获取验证码</td>
     * </tr>
     * <tr>
     * <td>431</td>
     * <td>验证码错误</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    @ResponseBody
    public String regist(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String loginName = request.getParameter("loginName");
        String realName = request.getParameter("realName");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        String myCode = request.getParameter("code");
        System.out.println(loginName);
        System.out.println(realName);
        System.out.println(password);
        System.out.println(repassword);
        System.out.println(email);
        System.out.println(myCode);
        if (loginName == null || loginName.trim().equals("")) {
            json.put("status", 400);
        } else if (isLoginNameExist(loginName)) {
            json.put("status", 401);
        } else if (password == null || password.trim().equals("") || password.length() < 6) {
            json.put("status", 410);
        } else if (!password.equals(repassword)) {
            json.put("status", 411);
        } else if (!Validator.validateEmail(email)) {
            json.put("status", 420);
        } else if (isEmailExist(email.toLowerCase())) {
            json.put("status", 421);
        } else if (validateInpEmailCode(request) == 400) {
            json.put("status", 430);
        } else if (validateInpEmailCode(request) == 401) {
            json.put("status", 431);
        } else {
            // 注册酒店
            Hotel hotel = (Hotel) request.getSession().getAttribute("registHotel");
            registToDatabase(hotel, loginName, password,realName, email);
            request.getSession().setAttribute("loginUserName", loginName);
            request.getSession().setAttribute("loginUserEmail",email);
            json.put("status", 200);
            json.put("url", "toComplete.do");
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "toComplete", method = RequestMethod.GET)
    public String toComplete(HttpServletRequest request) {

        return "regist_complete";
    }

    /**
     * 数据库初始化
     *
     * @param loginName
     * @param password
     * @param email
     */
    private void registToDatabase(Hotel hotel, String loginName, String password, String realName, String email) {
        User system = userService.getUserById(0);
        Date now = new Date();
        // 添加初始化请假类型
        // 事假
        LeaveType affair = new LeaveType();
        affair.setDeduct(0.0);
        affair.setName("affair");
        affair.setEntryDatetime(now);
        affair.setEntryUser(system);
        // 年假
        LeaveType year = new LeaveType();
        year.setDeduct(0.0);
        year.setName("year");
        year.setEntryDatetime(now);
        year.setEntryUser(system);
        // 婚假
        LeaveType marry = new LeaveType();
        marry.setDeduct(0.0);
        marry.setName("marry");
        marry.setEntryDatetime(now);
        marry.setEntryUser(system);
        // 丧假
        LeaveType funeral = new LeaveType();
        funeral.setDeduct(0.0);
        funeral.setName("funeral");
        funeral.setEntryDatetime(now);
        funeral.setEntryUser(system);
        // 病假
        LeaveType sick = new LeaveType();
        sick.setDeduct(0.0);
        sick.setName("sick");
        sick.setEntryDatetime(now);
        sick.setEntryUser(system);
        // 产假
        LeaveType maternity = new LeaveType();
        maternity.setDeduct(0.0);
        maternity.setName("maternity");
        maternity.setEntryDatetime(now);
        maternity.setEntryUser(system);

        List<LeaveType> leaveTypes = new ArrayList<LeaveType>();
        leaveTypes.add(affair);
        leaveTypes.add(year);
        leaveTypes.add(marry);
        leaveTypes.add(funeral);
        leaveTypes.add(sick);
        leaveTypes.add(maternity);

        // 添加默认物料类型
        MaterielType materielType = new MaterielType();
        materielType.setName("Other");

        // 添加角色
        Role role = new Role();
        role.setHotel(hotel);
        role.setEntryDatetime(now);
        role.setName("Admin(经理)");
        role.setEntryUser(system);
        role.setMenus(menuService.getAll());

        // 添加酒店员工ID配置
        Odom odom = new Odom();
        odom.setSequence(1);
        // 添加酒店
        hotel.setAddDate(now);
        hotel.setEntryDatetime(now);
        hotel.setEntryUser(system);
        hotel.setLeaveTypes(leaveTypes);
        hotel.getMaterielTypes().add(materielType);
        hotel.setIsClose(false);
        hotel.setOdom(odom);

        // 添加用户设置
        Setting setting = new Setting();
        setting.setLang("zh_CN");
        setting.setPageSize(10);

        // 添加用户
        User user = new User();
        user.setLoginName(loginName);
        user.setRealName(realName);
        user.setPassword(MD5Util.encrypt(password));
        user.setEmail(email.toLowerCase());
        user.setHotel(hotel);
        user.getRoles().add(role);
        user.setRegistDate(now);
        user.setEntryDatetime(now);
        user.setEntryUser(system);
        user.setSetting(setting);
        userService.saveUser(user);

    }

    /**
     * 验证用户名是否存在
     *
     * @param request
     * @return {"status": code}
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>用户存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>用户不存在</td>
     * </tr>
     * </table>
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
     * 验证邮箱是否存在
     *
     * @param request
     * @return {"status": code}
     * <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>邮箱存在</td>
     * </tr>
     * <tr>
     * <td>201</td>
     * <td>邮箱不存在</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>邮箱格式不正确</td>
     * </tr>
     * </table>
     */
    @RequestMapping(value = "validateEmail", method = RequestMethod.GET)
    @ResponseBody
    public String validateEmail(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        String email = request.getParameter("email");
        if (!Validator.validateEmail(email.toLowerCase())) {
            json.put("status", 400);
            return json.toJSONString();
        }
        if (isEmailExist(email)) {
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
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>true</td>
     * <td>用户存在</td>
     * </tr>
     * <tr>
     * <td>false</td>
     * <td>用户不存在</td>
     * </tr>
     * </table>
     */
    public boolean isLoginNameExist(String loginName) {
        if (userService.getUserByLoginName(loginName) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 根据邮箱查找用户是否存在
     *
     * @param email
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>true</td>
     * <td>用户存在</td>
     * </tr>
     * <tr>
     * <td>false</td>
     * <td>用户不存在</td>
     * </tr>
     * </table>
     */
    public boolean isEmailExist(String email) {
        if (userService.getUserByEmail(email.toLowerCase()) != null) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 验证 邮件验证码
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>验证通过</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有获取验证码</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>验证码错误</td>
     * </tr>
     * </table>
     */
    public int validateInpEmailCode(HttpServletRequest request) {
        String realCode = (String) request.getSession().getAttribute("registCode");
        String myCode = request.getParameter("code");
        System.out.println(realCode);
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
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>验证通过</td>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>没有输入验证码</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>验证码错误</td>
     * </tr>
     * </table>
     */
    public int validateInpPicCode(HttpServletRequest request, String myCode) {
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
