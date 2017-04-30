package com.wittymonkey.controller;

import com.alibaba.fastjson.JSONObject;
import com.wittymonkey.entity.Customer;
import com.wittymonkey.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by neilw on 2017/2/23.
 */
@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "findCustomer", method = RequestMethod.GET)
    @ResponseBody
    public String findCustomer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String idCard = request.getParameter("idCard");
        Customer customer = customerService.getCustomerByIdCard(idCard);
        if (customer != null) {
            jsonObject.put("status", 200);
            jsonObject.put("id", customer.getId());
            jsonObject.put("name", customer.getName());
            jsonObject.put("tel", customer.getTel());
        } else {
            jsonObject.put("status", 201);
        }
        return jsonObject.toJSONString();
    }
}
