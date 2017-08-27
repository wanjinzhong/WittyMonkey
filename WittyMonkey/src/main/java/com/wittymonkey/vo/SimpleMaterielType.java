package com.wittymonkey.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by neilw on 2017/3/25.
 */
public class SimpleMaterielType implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer materielNum;
    private String name;
    private String note;
    private Date entryDatetime;
    private String entryUser;
    private Boolean editable;
    private Boolean isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterielNum() {
        return materielNum;
    }

    public void setMaterielNum(Integer materielNum) {
        this.materielNum = materielNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
