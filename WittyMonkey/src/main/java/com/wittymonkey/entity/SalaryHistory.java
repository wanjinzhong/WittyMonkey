package com.wittymonkey.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by neilw on 2017/4/20.
 */
@Entity
@Table(name = "salary_history")
public class SalaryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Salary.class)
    @JoinColumn(name = "salary_id", referencedColumnName = "id")
    private Salary salary;

    // 基本工资
    @Column
    private double total;

    // 请假扣薪
    @Column(name = "leave_pay")
    private double leavePay;

    // 其它扣薪
    @Column(name = "other_pay")
    private double otherPay;

    // 奖金
    @Column
    private double bonus;

    // 实际工资
    @Column
    private double amount;

    // 工资日期
    @Column(name = "salary_date")
    private Date salaryDate;

    // 发放工资日期
    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @Column
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getLeavePay() {
        return leavePay;
    }

    public void setLeavePay(double leavePay) {
        this.leavePay = leavePay;
    }

    public double getOtherPay() {
        return otherPay;
    }

    public void setOtherPay(double otherPay) {
        this.otherPay = otherPay;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public User getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(User entryUser) {
        this.entryUser = entryUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
