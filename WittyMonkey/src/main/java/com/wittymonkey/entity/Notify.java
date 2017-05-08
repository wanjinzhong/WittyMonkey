package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通知，类似于站内信和邮件功能
 *
 * @author neilw
 */
@Entity
@Table(name = "notify")
public class Notify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender")
    private User sender;

    @OneToMany(targetEntity = NotifyReceiver.class, mappedBy = "notify", cascade = {CascadeType.ALL})
    private List<NotifyReceiver> receivers = new ArrayList<NotifyReceiver>();

    @Column(name = "send_date")
    private Date sendDate;

    @OneToMany(targetEntity = NotifyFile.class, mappedBy = "notify", cascade = {CascadeType.ALL})
    private List<NotifyFile> files = new ArrayList<NotifyFile>();

    // 紧急级别
    @Column
    private Integer level;

    // 主题
    @Column
    private String subject;

    // 是否放在垃圾箱里
    @Column
    private Boolean isInTrash;

    // 是否已删除（对发件人而言，收件人仍能看到）
    @Column
    private Boolean isDeleted;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<NotifyReceiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<NotifyReceiver> receivers) {
        this.receivers = receivers;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public List<NotifyFile> getFiles() {
        return files;
    }

    public void setFiles(List<NotifyFile> files) {
        this.files = files;
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

    public Boolean getInTrash() {
        return isInTrash;
    }

    public void setInTrash(Boolean inTrash) {
        isInTrash = inTrash;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
