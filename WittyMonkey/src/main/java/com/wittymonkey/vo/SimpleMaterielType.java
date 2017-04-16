package com.wittymonkey.vo;

import com.wittymonkey.entity.Hotel;
import com.wittymonkey.entity.Materiel;
import com.wittymonkey.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Integer getId() {
        return id;
    }

    public Integer getMaterielNum() {
        return materielNum;
    }

    public String getName() {
        return name;
    }

    public String getNote() {
        return note;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public String getEntryUser() {
        return entryUser;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMaterielNum(Integer materielNum) {
        this.materielNum = materielNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

}
