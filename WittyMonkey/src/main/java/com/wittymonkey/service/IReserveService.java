package com.wittymonkey.service;

import com.wittymonkey.entity.Reserve;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IReserveService {

    Reserve getReserveById(Integer reserveId);

    void save(Reserve reserve);

    List<Reserve> getReserveByRoomId(Integer roomId, Integer status, Integer start, Integer pageSize);

    /**
     * 根据时间获取该时间点所处的预定情况
     * @param roomId
     * @param date
     * @return
     */
    Reserve getReserveByDate(Integer roomId, Date date);

    Integer getTotalByRoomIdReserved(Integer roomId, Integer status);

    void update (Reserve reserve) throws SQLException;
}
