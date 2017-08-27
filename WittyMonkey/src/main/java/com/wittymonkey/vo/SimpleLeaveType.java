package com.wittymonkey.vo;

import java.util.Date;

/**
 * Created by neilw on 2017/4/26.
 */
public class SimpleLeaveType {
    private Integer id;

    private String name;

    private Double deduct;

    private String note;

    private Boolean deletable;

    private Date entryDatetime;

    private String entryUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDeduct() {
        return deduct;
    }

    public void setDeduct(Double deduct) {
        this.deduct = deduct;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
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
}
