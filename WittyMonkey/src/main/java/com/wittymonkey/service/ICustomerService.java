package com.wittymonkey.service;

import com.wittymonkey.entity.Customer;

/**
 * Created by neilw on 2017/2/23.
 */
public interface ICustomerService {
    Customer getCustomerByIdCard(String idCard);

    Customer getCustomerById(Integer id);
}
