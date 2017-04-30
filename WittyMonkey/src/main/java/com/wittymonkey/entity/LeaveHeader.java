package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假申请和记录
 *
 * @author neilw
 */
@Entity
@Table(name = "leave_header")
public class LeaveHeader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = LeaveDetail.class, mappedBy = "leaveHeader", cascade = {CascadeType.ALL})
    private List<LeaveDetail> leaveDetails = new ArrayList<LeaveDetail>();

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "apply_user_id", referencedColumnName = "id")
    private User applyUser;

    @Column(name = "apply_datetime")
    private Date applyDatetime;

    @Column(name = "apply_user_note", length = 1024)
    private String applyUserNote;

    // 申请状态（待审批/通过/驳回）
    @Column
    private Integer status;

    @ManyToOne(targetEntity = LeaveType.class)
    @JoinColumn(name = "leave_type_id", referencedColumnName = "id")
    private LeaveType leaveType;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @Column(name = "entry_user_note", length = 1024)
    private String entryUserNote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(User applyUser) {
        this.applyUser = applyUser;
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

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public User getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(User entryUser) {
        this.entryUser = entryUser;
    }

    public String getEntryUserNote() {
        return entryUserNote;
    }

    public void setEntryUserNote(String entryUserNote) {
        this.entryUserNote = entryUserNote;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public List<LeaveDetail> getLeaveDetails() {
        return leaveDetails;
    }

    public void setLeaveDetails(List<LeaveDetail> leaveDetails) {
        this.leaveDetails = leaveDetails;
    }
}