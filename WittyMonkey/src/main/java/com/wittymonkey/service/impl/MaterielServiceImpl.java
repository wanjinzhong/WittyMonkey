package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.entity.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IMaterielService;

import java.util.List;
import java.util.Map;

@Service(value="materielService")
public class MaterielServiceImpl implements IMaterielService{

    @Autowired
    private IMaterielDao materielDao;

    @Override
    public Integer getTotal(Map<Integer, Object> condition) {
        return materielDao.getTotal(condition);
    }

    @Override
    public List<Materiel> getMaterielByPage(Map<Integer, Object> condition, Integer start, Integer total) {
        return materielDao.getMaterielByPage(condition, start, total);
    }

    @Override
    public Materiel getMaterielByBarcode(Integer hotelId, String barcode) {
        return materielDao.getMaterielByBarcode(hotelId, barcode);
    }

    @Override
    public void saveMateriel(Materiel materiel) {
        materielDao.save(materiel);
    }
}
