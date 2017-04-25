package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.entity.InStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IInStockService;

import java.util.List;
import java.util.Map;

@Service(value="inStockService")
public class InStockServiceImpl implements IInStockService{

    @Autowired
    private IInStockDao inStockDao;

    @Override
    public Integer getTotal(Map<Integer, Object> param) {
        return inStockDao.getTotal(param);
    }

    @Override
    public List<InStock> getInStocks(Map<Integer, Object> param, Integer start, Integer total) {
        return inStockDao.getInStockByCondition(param, start, total);
    }
}
