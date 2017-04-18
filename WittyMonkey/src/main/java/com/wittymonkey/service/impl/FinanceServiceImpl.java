package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.entity.Finance;
import com.wittymonkey.entity.FinanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IFinanceService;

import java.util.Date;
import java.util.List;

@Service(value="financeService")
public class FinanceServiceImpl implements IFinanceService{

    @Autowired
    private IFinanceDao financeDao;

    @Override
    public Integer getTotal(Integer hotelId, Integer type, Date form, Date to) {
        return financeDao.getTotal(hotelId, type, form, to);
    }

    @Override
    public List<Finance> getFinanceByPage(Integer hotelId, Integer type, Date form, Date to, Integer start, Integer total) {
        return financeDao.getFinanceByPage(hotelId, type, form, to, start, total);
    }

    @Override
    public void save(Finance finance) {
        financeDao.save(finance);
    }
}
