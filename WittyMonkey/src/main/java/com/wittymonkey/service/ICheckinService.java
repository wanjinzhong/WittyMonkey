package com.wittymonkey.service;

import com.wittymonkey.entity.Checkin;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface ICheckinService {

    /**
     * 查询当前没有退房的入住信息
     *
     * @param roomId
     * @return
     */
    Checkin getCheckinByRoomUncomplete(Integer roomId);

    /**
     * 根据id查询入住
     *
     * @param id
     * @return
     */
    Checkin getCheckinById(Integer id);

    void update(Checkin checkin) throws SQLException;

    void checkin(Checkin checkin);

    List<Checkin> getCheckinViaEstCheckoutDate(Integer hotelId, Date date);
}
