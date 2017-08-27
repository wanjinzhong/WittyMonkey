package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Setting;
import com.wittymonkey.entity.User;
import com.wittymonkey.service.ISettingService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by neilw on 2017/4/28.
 */
@Controller
public class SettingController {

    @Autowired
    private ISettingService settingService;

    @RequestMapping(value = "getSetting", method = RequestMethod.GET)
    @ResponseBody
    public String getSetting(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        Setting setting = settingService.getSettingByUser(user.getId());
        json.put("setting", setting);
        return json.toJSONString();
    }

    /**
     * 保存设置
     *
     * @param request
     * @return <table border="1" cellspacing="0">
     * <tr>
     * <th>代码</th>
     * <th>说明</th>
     * </tr>
     * <tr>
     * <td>400</td>
     * <td>页数为空</td>
     * </tr>
     * <tr>
     * <td>401</td>
     * <td>页数不正确</td>
     * </tr>
     * <tr>
     * <td>200</td>
     * <td>保存成功</td>
     * </tr>
     */
    @RequestMapping(value = "saveSetting", method = RequestMethod.POST)
    @ResponseBody
    public String saveSetting(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        User user = (User) request.getSession().getAttribute("loginUser");
        String dataPerPage = request.getParameter("dataPerPage");
        String lang = request.getParameter("lang").trim();
        if (StringUtils.isBlank(dataPerPage)) {
            json.put("status", 400);
            return json.toJSONString();
        }
        Integer pageSize;
        try {
            pageSize = Integer.parseInt(dataPerPage.trim());
        } catch (NumberFormatException e) {
            json.put("status", 401);
            return json.toJSONString();
        }
        Setting setting = settingService.getSettingByUser(user.getId());
        setting.setPageSize(pageSize);
        setting.setLang(lang);
        settingService.saveSetting(setting);
        json.put("status", 200);
        return json.toJSONString();
    }
}
