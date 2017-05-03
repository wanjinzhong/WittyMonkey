package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.dao.IOutStockDao;
import com.wittymonkey.entity.InStock;
import com.wittymonkey.entity.Materiel;
import com.wittymonkey.entity.OutStock;
import com.wittymonkey.service.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service(value = "materielService")
public class MaterielServiceImpl implements IMaterielService {

    @Autowired
    private IMaterielDao materielDao;

    @Autowired
    private IInStockDao inStockDao;

    @Autowired
    private IOutStockDao outStockDao;

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
    public Materiel getMaterielByName(Integer hotelId, String name) {
        return materielDao.getMaterielByName(hotelId, name);
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
        List<OutStock> outStocks = outStockDao.getOutStockByMateriel(materiel.getId());
        for (InStock inStock : inStocks) {
            inStock.setMateriel(null);
            inStockDao.save(inStock);
        }
        for (OutStock outStock : outStocks) {
            outStock.setMateriel(null);
            outStockDao.save(outStock);
        }
        materielDao.delete(materiel);
    }

    @Override
    public List<Materiel> getMaterielLowInventory(Integer hotelId) {
        return materielDao.getMaterielLowInventory(hotelId);
    }
}
