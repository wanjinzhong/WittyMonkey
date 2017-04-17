package com.wittymonkey.service;

import com.wittymonkey.entity.Finance;
import com.wittymonkey.entity.FinanceType;

import java.util.List;

public interface IFinanceService {

    Integer getTotal(Integer hotelId, Integer income);

    List<Finance> getFinanceByPage(Integer hotelId, Integer income, Integer start, Integer total);

}
