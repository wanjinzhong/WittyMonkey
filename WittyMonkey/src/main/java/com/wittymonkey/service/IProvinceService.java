package com.wittymonkey.service;

import com.wittymonkey.entity.Province;

import java.util.List;

public interface IProvinceService {

    /**
     * 获取所有省
     *
     * @return
     */
    List<Province> getAll();

    /**
     * 根据省代码获取省
     *
     * @param code
     * @return
     */
    Province getProvinceByCode(Integer code);
}
