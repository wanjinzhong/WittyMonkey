package com.wittymonkey.vo;

import com.wittymonkey.entity.Finance;
import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/14.
 */
public class SimpleFinanceType implements Serializable {

    private Integer id;

    private Boolean income;

    private String name;

    private String hotel;

    //private List<Finance> finances = new ArrayList<Finance>();

    private String note;

    private Date entryDatetime;

    private String entryUser;

    private Boolean editable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }
}
