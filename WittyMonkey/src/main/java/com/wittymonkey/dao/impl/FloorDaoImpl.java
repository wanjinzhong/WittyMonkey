package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IFloorDao;
import com.wittymonkey.entity.Floor;

import java.util.HashMap;
import java.util.Map;

@Repository(value="floorDao")
public class FloorDaoImpl extends GenericDaoImpl<Floor> implements IFloorDao{

    @Override
    public Floor getFloorByNo(Integer hotelId, Integer floorNo) {
        String hql = "from Floor where hotel.id = :hotelId and floorNo = :floorNo";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("hotelId", hotelId);
        param.put("floorNo", floorNo);
        return queryOneHql(hql,param);
    }
}
