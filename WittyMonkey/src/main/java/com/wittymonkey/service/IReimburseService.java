package com.wittymonkey.service;

import com.wittymonkey.entity.Reimburse;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IReimburseService {
    Reimburse getReimburseById(Integer id);

    Integer getTotal(Integer hotelId, Integer status, Date from, Date to);

    List<Reimburse> getReimburseByPage(Integer hotelId, Integer status, Date from, Date to, Integer start, Integer total);

    Integer getTotalByUser(Integer userId, Integer status, Date from, Date to);

    List<Reimburse> getReimburseByUser(Integer userId, Integer status, Date from, Date to, Integer start, Integer total);

    void save(Reimburse reimburse);

    void delete(Reimburse reimburse) throws SQLException;
}
