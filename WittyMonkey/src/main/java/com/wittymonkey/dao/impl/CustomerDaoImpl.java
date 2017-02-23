package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.ICustomerDao;
import com.wittymonkey.dao.IGenericDao;
import com.wittymonkey.entity.Customer;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by neilw on 2017/2/23.
 */
@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements ICustomerDao {
    @Override
    public Customer getCustomerByIdCard(String idCard) {
        String hql = "from Customer where idCard = ?";
        return queryOneHql(hql, idCard);
    }

    @Override
    public Customer getCustomerById(Integer id) {
        String hql = "from Customer where id = ?";
        return queryOneHql(hql, id);
    }
}
