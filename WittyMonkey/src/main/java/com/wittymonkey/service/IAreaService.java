package com.wittymonkey.service;

import com.wittymonkey.entity.Area;
import com.wittymonkey.entity.City;

import java.util.List;

public interface IAreaService {

    public Area getAreaByCode(Integer code);

    public List<Area> getAllByCity(City city);

    public List<Area> getAllByCity(Integer code);
}
