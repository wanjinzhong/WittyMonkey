package com.wittymonkey.vo;

import com.wittymonkey.entity.Floor;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by neilw on 2017/2/16.
 */
public class SimpleFloor implements Serializable{

    private Integer id;
    private Integer floorNo;
    private Integer roomNum;
    private String entryUser;
    private Date entryDatetime;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public String getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }
}
