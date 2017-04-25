package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.entity.InStock;
import com.wittymonkey.entity.Materiel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wittymonkey.service.IMaterielService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service(value="materielService")
public class MaterielServiceImpl implements IMaterielService{

    @Autowired
    private IMaterielDao materielDao;

    @Autowired
    private IInStockDao inStockDao;

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

    @Override
    public Materiel getMaterielById(Integer id) {
        return materielDao.getMaterielById(id);
    }

    @Override
    public void deleteMateriel(Materiel materiel) throws SQLException {
        List<InStock> inStocks = inStockDao.getInStockByMateriel(materiel.getId());
        for (InStock inStock : inStocks){
            inStock.setMateriel(null);
            inStockDao.save(inStock);
        }
        materielDao.delete(materiel);
    }
}
