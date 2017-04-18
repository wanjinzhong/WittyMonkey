package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.entity.Finance;
import com.wittymonkey.entity.FinanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IFinanceService;

import java.util.List;

@Service(value="financeService")
public class FinanceServiceImpl implements IFinanceService{

    @Autowired
    private IFinanceDao financeDao;

    @Override
    public Integer getTotal(Integer hotelId, Integer type) {
        return financeDao.getTotal(hotelId, type);
    }

    @Override
    public List<Finance> getFinanceByPage(Integer hotelId, Integer income, Integer start, Integer total) {
        return financeDao.getFinanceByPage(hotelId, income, start, total);
    }

    @Override
    public void save(Finance finance) {
        financeDao.save(finance);
    }
}
