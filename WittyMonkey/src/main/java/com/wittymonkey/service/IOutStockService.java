package com.wittymonkey.service;

import com.wittymonkey.entity.OutStock;

import java.util.List;
import java.util.Map;

public interface IOutStockService {

    Integer getTotal(Map<Integer, Object> param);

    List<OutStock> getOutStocks(Map<Integer, Object> param, Integer start, Integer total);

    void save(OutStock outStock);
}
