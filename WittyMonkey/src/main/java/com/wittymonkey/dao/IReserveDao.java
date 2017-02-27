package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wittymonkey.entity.Reserve;

public interface IReserveDao extends IGenericDao<Reserve, Serializable>{


    /**
     * 根据房间id获取指定状态下的预定信息
     * @param roomId
     * @return
     */
    List<Reserve> getReserveByRoomId(Integer roomId, Integer status);

    Reserve getReserveByDate(Integer roomId, Date date);
}
