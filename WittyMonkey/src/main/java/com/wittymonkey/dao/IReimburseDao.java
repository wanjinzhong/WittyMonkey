package com.wittymonkey.dao;

import com.wittymonkey.entity.Reimburse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface IReimburseDao extends IGenericDao<Reimburse, Serializable>{

    Reimburse getReimburseById(Integer id);

    Integer getTotal(Integer hotelId, Integer status, Date from, Date to);

    List<Reimburse> getReimburseByPage(Integer hotelId, Integer status, Date from, Date to, Integer start, Integer total );
}
