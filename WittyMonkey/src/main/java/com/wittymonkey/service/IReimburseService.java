package com.wittymonkey.service;

import com.wittymonkey.entity.Reimburse;

import java.util.Date;
import java.util.List;

public interface IReimburseService {

    Integer getTotal(Integer hotelId, Integer status, Date from, Date to);

    List<Reimburse> getReimburseByPage(Integer hotelId, Integer status, Date from, Date to, Integer start, Integer total );
}
