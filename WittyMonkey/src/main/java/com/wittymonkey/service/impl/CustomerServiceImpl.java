package com.wittymonkey.service.impl;

import com.wittymonkey.dao.ICustomerDao;
import com.wittymonkey.entity.Customer;
import com.wittymonkey.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by neilw on 2017/2/23.
 */
@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private ICustomerDao customerDao;

    @Override
    public Customer getCustomerByIdCard(String idCard) {
        return customerDao.getCustomerByIdCard(idCard);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customerDao.getCustomerById(id);
    }
}
