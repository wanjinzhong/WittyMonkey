package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.entity.InStock;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IInStockService;

import java.util.Date;
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
        if (param.containsKey(Constraint.INSTOCK_SEARCH_CONDITION_TO)){
            param.put(Constraint.INSTOCK_SEARCH_CONDITION_TO, DateUtil.addOneDay((Date) param.get(Constraint.INSTOCK_SEARCH_CONDITION_TO)));
        }
        return inStockDao.getInStockByCondition(param, start, total);
    }
}
