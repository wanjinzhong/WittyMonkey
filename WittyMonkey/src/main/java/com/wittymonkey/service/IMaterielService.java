package com.wittymonkey.service;

import com.wittymonkey.entity.Materiel;

import java.util.List;
import java.util.Map;

public interface IMaterielService {
    Integer getTotal(Map<Integer, Object> condition);

    List<Materiel> getMaterielByPage(Map<Integer, Object> condition, Integer start, Integer total);
}
