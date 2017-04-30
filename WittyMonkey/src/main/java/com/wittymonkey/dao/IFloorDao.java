package com.wittymonkey.dao;

import com.wittymonkey.entity.Floor;

import java.io.Serializable;
import java.util.List;

public interface IFloorDao extends IGenericDao<Floor, Serializable> {

    Floor getFloorById(Integer id);

    Floor getFloorByNo(Integer hotelId, Integer floorNo);

    List<Floor> getFloorByPage(Integer hotelId, Integer first, Integer total);

    Integer getTotal(Integer hotelId);
}
