package com.wittymonkey.dao.impl;

import org.springframework.stereotype.Repository;

import com.wittymonkey.dao.ISalaryRecordDao;
import com.wittymonkey.entity.SalaryRecord;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value="salaryRecordDao")
public class SalaryRecordDaoImpl extends GenericDaoImpl<SalaryRecord> implements ISalaryRecordDao{

    @Override
    public Integer getTotal(Integer salaryId) {
        String hql = "select count(1) from SalaryRecord where salary.id = ?";
        return countHQL(hql, salaryId);
    }

    @Override
    public List<SalaryRecord> getSalaryRecordByPage(Integer salaryId, Integer start, Integer total) {
        String hql = "from SalaryRecord where salary.id = ? order By startDate desc";
        return queryListHQL(hql,salaryId, start, total);
    }

    @Override
    public SalaryRecord getSalaryRecordByStartDate(Integer salaryId, Date startDate) {
        String hql = "from SalaryRecord where startDate = :startDate and salary.id = :salaryId";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("startDate", startDate);
        param.put("salaryId", salaryId);
        return queryOneHql(hql, param);
    }

    @Override
    public SalaryRecord getSalaryRecordAtDate(Integer salaryId, Date date) {
        String hql = "from SalaryRecord where startDate <= :date and salary.id = :salaryId order by startDate desc";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("date", date);
        param.put("salaryId", salaryId);
        return queryOneHql(hql, param);
    }

    @Override
    public SalaryRecord getSalaryRecordById(Integer id) {
        String hql = "from SalaryRecord where id = ?";
        return queryOneHql(hql, id);
    }

    @Override
    public List<SalaryRecord> getSalaryRecordByDateRange(Integer salaryId, Date startDate, Date endDate) {
        String hql = "from SalaryRecord where salary.id = :salaryId and startDate > :startDate and startDate < :endDate";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("salaryId", salaryId);
        param.put("startDate", startDate);
        param.put("endDate", endDate);
        return queryListHQL(hql, param);
    }
}
