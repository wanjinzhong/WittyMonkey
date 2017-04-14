package com.wittymonkey.service;

import com.wittymonkey.entity.FinanceType;

import java.util.List;

public interface IFinanceTypeService {

    List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total);

    Integer getTotal(Integer hotelId);

    FinanceType getFinanceTypeByName(Integer hotelId, String name);

    void save(FinanceType financeType);
}
