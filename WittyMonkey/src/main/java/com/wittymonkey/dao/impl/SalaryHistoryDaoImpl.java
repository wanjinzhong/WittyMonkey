package com.wittymonkey.dao.impl;

import com.wittymonkey.dao.ISalaryHistoryDao;
import com.wittymonkey.entity.SalaryHistory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by neilw on 2017/4/21.
 */
@Repository(value = "salaryHistoryDao")
public class SalaryHistoryDaoImpl extends GenericDaoImpl<SalaryHistory> implements ISalaryHistoryDao {

    @Override
    public List<SalaryHistory> getSalaryHistoryBySalaryId(Integer salaryId) {
        String hql = "from SalaryHistory where salary.id = ? order by salaryDate";
        return queryListHQL(hql, salaryId);
    }

    @Override
    public SalaryHistory getSalaryHistoryByUserIdAndSalaryDate(Integer userId, Date salaryDate) {
        String hql = "from SalaryHistory where salaryDate = :salaryDate and salary.staff.id = :userId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        param.put("salaryDate", salaryDate);
        return queryOneHql(hql, param);
    }

    @Override
    public List<SalaryHistory> getSalaryByDateRange(Date from, Date to) {
        String hql = "from SalaryHistory where salaryDate >= :from and salaryDate <= :to";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("from", from);
        param.put("to", to);
        return queryListHQL(hql, param);
    }
}
