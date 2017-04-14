package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.FinanceType;
import com.wittymonkey.entity.Hotel;

public interface IFinanceTypeDao extends IGenericDao<FinanceType, Serializable>{

    List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total);

    Integer getTotal(Integer hotelId);

    FinanceType getFinanceTypeByName(Integer hotelId, String name);
}
