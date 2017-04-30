package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IProvinceDao;
import com.wittymonkey.entity.Province;
import com.wittymonkey.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "provinceService")
public class ProvinceServiceImpl implements IProvinceService {

    @Autowired
    private IProvinceDao provinceDao;

    @Override
    public List<Province> getAll() {
        return provinceDao.getAll();
    }

    @Override
    public Province getProvinceByCode(Integer code) {
        return provinceDao.getProvinceByCode(code);
    }

}
