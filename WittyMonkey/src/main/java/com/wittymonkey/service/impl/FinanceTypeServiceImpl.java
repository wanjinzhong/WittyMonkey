package com.wittymonkey.service.impl;

import com.wittymonkey.dao.IFinanceDao;
import com.wittymonkey.dao.IFinanceTypeDao;
import com.wittymonkey.dao.IUserDao;
import com.wittymonkey.entity.Finance;
import com.wittymonkey.entity.FinanceType;
import com.wittymonkey.service.IFinanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service(value = "financeTypeService")
public class FinanceTypeServiceImpl implements IFinanceTypeService {

    @Autowired
    private IFinanceTypeDao financeTypeDao;

    @Autowired
    private IFinanceDao financeDao;

    @Autowired
    private IUserDao userDao;

    @Override
    public List<FinanceType> getFinanceTypeByPage(Integer hotelId, Integer type, Integer start, Integer total) {
        return financeTypeDao.getFinanceTypeByPage(hotelId, type, start, total);
    }

    @Override
    public Integer getTotal(Integer hotelId) {
        return financeTypeDao.getTotal(hotelId);
    }

    @Override
    public FinanceType getFinanceTypeByName(Integer hotelId, String name) {
        return financeTypeDao.getFinanceTypeByName(hotelId, name);
    }

    @Override
    public void save(FinanceType financeType) {
        financeTypeDao.save(financeType);
    }

    @Override
    public FinanceType getFinanceTypeById(Integer id) {
        return financeTypeDao.getFinanceTypeById(id);
    }

    @Override
    public void delete(FinanceType financeType, Integer hotelId, Integer userId) throws SQLException {
        List<Finance> finances = financeDao.getFinanceByType(financeType.getId());
        FinanceType inType = financeTypeDao.getDefaultType(true, hotelId);
        FinanceType outType = financeTypeDao.getDefaultType(false, hotelId);
        Date now = new Date();
        for (Finance finance : finances) {
            finance.setFinanceType(finance.getFinanceType().getIncome() ? inType : outType);
            finance.setEntryUser(userDao.getUserById(userId));
            finance.setEntryDatetime(now);
        }
        financeDao.batchUpdate(finances);
        financeTypeDao.delete(financeType);
    }
}
