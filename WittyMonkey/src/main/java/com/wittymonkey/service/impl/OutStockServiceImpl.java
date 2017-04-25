package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.dao.IOutStockDao;
import com.wittymonkey.entity.InStock;
import com.wittymonkey.entity.OutStock;
import com.wittymonkey.util.DateUtil;
import com.wittymonkey.vo.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IOutStockService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="outStockService")
public class OutStockServiceImpl implements IOutStockService{
    @Autowired
    private IOutStockDao outStockDao;

    @Override
    public Integer getTotal(Map<Integer, Object> param) {
        return outStockDao.getTotal(param);
    }

    @Override
    public List<OutStock> getOutStocks(Map<Integer, Object> param, Integer start, Integer total) {
        if (param.containsKey(Constraint.OUTSTOCK_SEARCH_CONDITION_TO)){
            param.put(Constraint.OUTSTOCK_SEARCH_CONDITION_TO, DateUtil.addOneDay((Date) param.get(Constraint.INSTOCK_SEARCH_CONDITION_TO)));
        }
        return outStockDao.getOutStockByCondition(param, start, total);
    }

    @Override
    public void save(OutStock outStock) {
        outStockDao.save(outStock);
    }
}
