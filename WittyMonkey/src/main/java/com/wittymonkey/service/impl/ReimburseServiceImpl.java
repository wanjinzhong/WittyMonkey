package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IReimburseDao;
import com.wittymonkey.entity.Reimburse;
import com.wittymonkey.service.IReimburseService;
import com.wittymonkey.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value="reimburseService")
public class ReimburseServiceImpl implements IReimburseService{

    @Autowired
    private IReimburseDao reimburseDao;

    @Override
    public Reimburse getReimburseById(Integer id) {
        return reimburseDao.getReimburseById(id);
    }

    @Override
    public Integer getTotal(Integer hotelId, Integer status, Date from, Date to) {
        return reimburseDao.getTotal(hotelId, status, from, DateUtil.addOneDay(to));
    }

    @Override
    public List<Reimburse> getReimburseByPage(Integer hotelId, Integer status, Date from, Date to, Integer start, Integer total) {
        return reimburseDao.getReimburseByPage(hotelId, status, from, DateUtil.addOneDay(to), start, total);
    }

    @Override
    public void save(Reimburse reimburse) {
        reimburseDao.save(reimburse);
    }
}
