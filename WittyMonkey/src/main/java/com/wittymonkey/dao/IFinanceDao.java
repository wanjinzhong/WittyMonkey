package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.Finance;

public interface IFinanceDao extends IGenericDao<Finance, Serializable>{
    List<Finance> getFinanceByType(Integer typeId);

    Integer getTotal(Integer hotelId, Integer income);

    List<Finance> getFinanceByPage(Integer hotelId, Integer income, Integer start, Integer total);
}
