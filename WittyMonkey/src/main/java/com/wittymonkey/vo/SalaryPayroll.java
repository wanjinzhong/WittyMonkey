package com.wittymonkey.vo;

import java.util.Date;

/**
 * Created by neilw on 2017/4/30.
 */
public class SalaryPayroll {

    private Integer staffId;

    private String staffNo;

    private String staffName;

    private Date salaryDate;

    private Double basic;

    private Double leave;

    private Double other;

    private Double bonus;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getLeave() {
        return leave;
    }

    public void setLeave(Double leave) {
        this.leave = leave;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }
}
