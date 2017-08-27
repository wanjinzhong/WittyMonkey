package com.wittymonkey.vo;

import java.util.Date;

/**
 * Created by neilw on 2017/4/18.
 */
public class SimpleReimbuse {
    private Integer id;

    private String applyUser;

    private Double money;

    private Integer status;

    private String applyUserNote;

    private Date applyDatetime;

    private Date entryDatetime;

    private String entryUser;

    private String entryUserNote;

    private String hotel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApplyUserNote() {
        return applyUserNote;
    }

    public void setApplyUserNote(String applyUserNote) {
        this.applyUserNote = applyUserNote;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
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

    public String getEntryUserNote() {
        return entryUserNote;
    }

    public void setEntryUserNote(String entryUserNote) {
        this.entryUserNote = entryUserNote;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
