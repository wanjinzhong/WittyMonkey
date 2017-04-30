package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.dao.IMaterielTypeDao;
import com.wittymonkey.entity.Materiel;
import com.wittymonkey.entity.MaterielType;
import com.wittymonkey.service.IMaterielTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service(value = "materielTypeService")
public class MaterielTypeServiceImpl implements IMaterielTypeService {

    @Autowired
    private IMaterielTypeDao materielTypeDao;

    @Autowired
    private IMaterielDao materielDao;

    @Override
    public void saveMaterielType(MaterielType materielType) {
        materielTypeDao.save(materielType);
    }

    @Override
    public Integer getTotalByHotelId(Integer hotelId) {
        return materielTypeDao.getTotalByHotelId(hotelId);
    }

    @Override
    public List<MaterielType> getMaterielTypeByHotelId(Integer hotelId, Integer start, Integer pageSize) {
        return materielTypeDao.getMaterielTypeByHotelId(hotelId, start, pageSize);
    }

    @Override
    public MaterielType getMaterielTypeByName(Integer hotelId, String name) {
        return materielTypeDao.getMaterielTypeByName(hotelId, name);
    }

    @Override
    public MaterielType getMaterielTypeById(Integer id) {
        return materielTypeDao.getMaterielTypeById(id);
    }

    @Override
    public void deleteMaterielType(MaterielType materielType) throws SQLException {
        MaterielType defaultType = materielTypeDao.getDefaultByHotel(materielType.getHotel().getId());
        List<Materiel> materiels = materielDao.getMaterielByType(materielType.getId());
        for (Materiel materiel : materiels) {
            materiel.setMaterielType(defaultType);
            materielDao.save(materiel);
        }
        materielTypeDao.delete(materielType);
    }
}
