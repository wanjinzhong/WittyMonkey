package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.IMaterielDao;
import com.wittymonkey.entity.Materiel;

import java.util.List;

@Repository(value="materielDao")
public class MaterielDaoImpl extends GenericDaoImpl<Materiel> implements IMaterielDao{

    @Override
    public List<Materiel> getMaterielByType(Integer typeId) {
        String hql = "from Materiel where materielType.id = ?";
        return queryListHQL(hql, typeId);
    }
}
