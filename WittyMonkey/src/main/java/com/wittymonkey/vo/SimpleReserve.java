package com.wittymonkey.vo;

import com.wittymonkey.entity.Customer;

import java.util.Date;

/**
 * Created by neilw on 2017/3/8.
 */
public class SimpleReserve {
    private Integer id;
    private Customer customer;
    private Date reserveDate;
    private Date estCheckinDate;
    private Date estCheckoutDate;
    private Double deposit;
    private Integer status;
    private Date entryDatetime;
    private String entryUser;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getReserveDate() {
        return reserveDate;
    }

    public void setReserveDate(Date reserveDate) {
        this.reserveDate = reserveDate;
    }

    public Date getEstCheckinDate() {
        return estCheckinDate;
    }

    public void setEstCheckinDate(Date estCheckinDate) {
        this.estCheckinDate = estCheckinDate;
    }

    public Date getEstCheckoutDate() {
        return estCheckoutDate;
    }

    public void setEstCheckoutDate(Date estCheckoutDate) {
        this.estCheckoutDate = estCheckoutDate;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public String getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
