package com.wittymonkey.vo;

import java.util.Date;

/**
 * Created by neilw on 2017/4/26.
 */
public class SimpleLeave {
    private Integer id;

    private Double deduct;

    private Date from;

    private Date to;

    private Double days;

    private String applyUser;

    private Date applyDatetime;

    private String applyUserNote;

    private Integer status;

    private String leaveType;

    private Date entryDatetime;

    private String entryUser;

    private String entryUserNote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDeduct() {
        return deduct;
    }

    public void setDeduct(Double deduct) {
        this.deduct = deduct;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApplyUserNote() {
        return applyUserNote;
    }

    public void setApplyUserNote(String applyUserNote) {
        this.applyUserNote = applyUserNote;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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
}
