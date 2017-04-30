package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IAreaDao;
import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;
import com.wittymonkey.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "areaService")
public class AreaServiceImpl implements IAreaService {

    @Autowired
    private IAreaDao areaDao;

    @Override
    public Area getAreaByCode(Integer code) {
        return areaDao.getAreaByCode(code);
    }

    @Override
    public List<Area> getAllByCity(City city) {
        return areaDao.getAllByCity(city.getCode());
    }

    @Override
    public List<Area> getAllByCity(Integer code) {
        return areaDao.getAllByCity(code);
    }

}
