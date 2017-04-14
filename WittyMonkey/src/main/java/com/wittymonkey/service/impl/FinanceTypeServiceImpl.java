package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.entity.FinanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IFinanceTypeService;

import java.util.List;

@Service(value="financeTypeService")
public class FinanceTypeServiceImpl implements IFinanceTypeService{

    @Autowired
    private IFinanceTypeDao financeTypeDao;

    @Override
    public List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total) {
        return financeTypeDao.getFinanceTypeByPage(hotelId, type, start, total);
    }

    @Override
    public Integer getTotal(Integer hotelId) {
        return financeTypeDao.getTotal(hotelId);
    }

    @Override
    public FinanceType getFinanceTypeByName(Integer hotelId, String name) {
        return financeTypeDao.getFinanceTypeByName(hotelId, name);
    }

    @Override
    public void save(FinanceType financeType) {
        financeTypeDao.save(financeType);
    }
}
