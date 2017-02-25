package com.wittymonkey.service;

import com.wittymonkey.entity.Reserve;

import java.util.List;

public interface IReserveService {

    void save(Reserve reserve);

    List<Reserve> getReserveByRoomId(Integer roomId, Integer status);
}
