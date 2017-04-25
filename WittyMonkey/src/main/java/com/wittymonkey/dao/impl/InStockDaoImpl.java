package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IInStockDao;
import com.wittymonkey.entity.InStock;

import java.util.List;

@Repository(value="inStockDao")
public class InStockDaoImpl extends GenericDaoImpl<InStock> implements IInStockDao{

    @Override
    public List<InStock> getInStockByMateriel(Integer materielId) {
        String hql = "from InStock where materiel.id = ?";
        return queryListHQL(hql, materielId);
    }
}
