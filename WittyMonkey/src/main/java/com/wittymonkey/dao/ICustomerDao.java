package com.wittymonkey.dao;

import com.wittymonkey.entity.Customer;

import java.io.Serializable;

/**
 * Created by neilw on 2017/2/23.
 */
public interface ICustomerDao extends IGenericDao<Customer, Serializable> {

    Customer getCustomerByIdCard(String idCard);

    Customer getCustomerById(Integer id);
}
