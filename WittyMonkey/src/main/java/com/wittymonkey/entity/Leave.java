package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 请假申请和记录
 * @author neilw
 *
 */
@Entity
@Table(name = "leave_record")
public class Leave implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column
	private Double deduct;

	@Column(name="from_date")
	private Date from;

	@Column(name="to_date")
	private Date to;

	@Column
	private Double days;

	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="apply_user_id", referencedColumnName="id")
	private User applyUser;

	@Column(name="apply_datetime")
	private Date applyDatetime;

	@Column(name="apply_user_note",length=1024)
	private String applyUserNote;

	// 申请状态（待审批/通过/驳回）
	@Column
	private Integer status;

	@ManyToOne(targetEntity=LeaveType.class)
	@JoinColumn(name="leave_type_id", referencedColumnName="id")
	private LeaveType leaveType;

	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
	@Column(name="entry_user_note", length=1024)
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

	public Double getDeduct() {
		return deduct;
	}

	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
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
}
