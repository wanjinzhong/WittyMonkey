package com.wittymonkey.service;

import com.wittymonkey.entity.Floor;

import java.sql.SQLException;
import java.util.List;

public interface IFloorService {

    /**
     * 根据id查找楼层
     *
     * @param id
     * @return
     */
    Floor getFloorById(Integer id);

    /**
     * 根据楼层号查找楼层
     *
     * @param hotelId
     * @param floorNo
     * @return
     */
    Floor getFloorByNo(Integer hotelId, Integer floorNo);

    /**
     * 判断楼层是否存在
     *
     * @param hotelId
     * @param floorNo
     * @return
     */
    Boolean isFloorExist(Integer hotelId, Integer floorNo);

    void saveFloor(Floor floor);

    void updateFloor(Floor floor) throws SQLException;

    /**
     * 删除楼层，移到楼层下的所有房间到未定义楼层
     *
     * @param floor
     * @throws SQLException
     */
    void deleteFloor(Floor floor) throws SQLException;

    List<Floor> getFloorByHotel(Integer hotelId, Integer start, Integer total);

    Integer getTotal(Integer hotelId);
}
