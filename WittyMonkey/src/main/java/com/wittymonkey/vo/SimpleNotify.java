package com.wittymonkey.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/5/3.
 */
public class SimpleNotify {

    private Integer notifyId;

    private Integer recieverId;

    private String sender;

    private List<String> receivers = new ArrayList<String>();

    private Date sendDate;

    private Integer level;

    private String subject;

    private String content;

    private Boolean isReaded;

    private Boolean isDelete;

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public Integer getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Integer recieverId) {
        this.recieverId = recieverId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getReaded() {
        return isReaded;
    }

    public void setReaded(Boolean readed) {
        isReaded = readed;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
