package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.FinanceType;

public interface IFinanceTypeDao extends IGenericDao<FinanceType, Serializable>{

    FinanceType getFinanceTypeById(Integer id);

    List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total);

    Integer getTotal(Integer hotelId);

    FinanceType getFinanceTypeByName(Integer hotelId, String name);

    FinanceType getDefaultType(Boolean income, Integer hotelId);
}
