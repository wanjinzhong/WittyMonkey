package com.wittymonkey.dao;

import java.io.Serializable;
import java.util.List;

import com.wittymonkey.entity.Floor;

public interface IFloorDao extends IGenericDao<Floor, Serializable>{

    Floor getFloorByNo(Integer hotelId, Integer floorNo);

    List<Floor> getFloorByPage(Integer hotelId, Integer first, Integer total);

    Integer getTotal(Integer hotelId);
}
