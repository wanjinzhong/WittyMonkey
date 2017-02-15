package com.wittymonkey.service;

import com.wittymonkey.entity.Floor;

public interface IFloorService {

    /**
     * 根据楼层号查找楼层
     * @param hotelId
     * @param floorNo
     * @return
     */
    Floor getFloorByNo(Integer hotelId, Integer floorNo);

    /**
     * 判断楼层是否存在
     * @param hotelId
     * @param floorNo
     * @return
     */
    Boolean isFloorExist(Integer hotelId, Integer floorNo);

    void saveFloor(Floor floor);
}
