package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.ISalaryHistoryDao;
import com.wittymonkey.entity.SalaryHistory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by neilw on 2017/4/21.
 */
@Repository(value = "salaryHistoryDao")
public class SalaryHistoryDaoImpl extends GenericDaoImpl<SalaryHistory> implements ISalaryHistoryDao{

    @Override
    public List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId) {
        String hql = "from SalaryHistory where salary.id = ? order by salaryDate";
        return queryListHQL(hql, salaryId);
    }
}
