package com.wittymonkey.vo;

import com.wittymonkey.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neilw on 2017/4/19.
 */
public class SimpleSalary {
    private Integer id;

    private String staff;

    private String staffNo;

    private List<SimpleSalaryRecord> salaryRecords = new ArrayList<SimpleSalaryRecord>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public List<SimpleSalaryRecord> getSalaryRecords() {
        return salaryRecords;
    }

    public void setSalaryRecords(List<SimpleSalaryRecord> salaryRecords) {
        this.salaryRecords = salaryRecords;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
}
