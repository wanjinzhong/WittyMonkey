package com.wittymonkey.service;

import com.wittymonkey.entity.Finance;

import java.util.Date;
import java.util.List;

public interface IFinanceService {
    /**
     * @param hotelId
     * @param type    -2: 查询所有收入，-1：查询所有支出，null：查询所有，其它：financeType的id
     * @param form
     * @param to
     * @return
     */
    Integer getTotal(Integer hotelId, Integer type, Date form, Date to);

    /**
     * @param hotelId
     * @param type:   -2: 查询所有收入，-1：查询所有支出，null：查询所有，其它：financeType的id
     * @param from
     * @param to
     * @param start
     * @param total
     * @return
     */
    List<Finance> getFinanceByPage(Integer hotelId, Integer type, Date from, Date to, Integer start, Integer total);

    void save(Finance finance);
}
