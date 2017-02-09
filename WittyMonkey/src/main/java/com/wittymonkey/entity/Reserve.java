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
 * 预定
 * @author neilw
 *
 */
@Entity
@Table
public class Reserve implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cust_name", length=20)
	private String custName;
	
	@Column(name="cust_idcard_no", length=20)
	private String custIdcardNo;
	
	@Column(name="cust_tel", length=20)
	private String custTel;
	
	@ManyToOne(targetEntity=RoomMaster.class)
	@JoinColumn(name="room_id", referencedColumnName="id")
	private RoomMaster room;
	
	// 预定时间
	@Column(name="reserve_date")
	private Date reserveDate;
	
	// 预计入住时间
	@Column(name="est_checkin_date")
	private Date estCheckinDate;
	
	//定金
	@Column
	private Double deposit;
	
	// 状态（待入住/已入住/已取消）
	@Column
	private Integer status;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;

	@Column(length=1024)
	private String note;
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustIdcardNo() {
		return custIdcardNo;
	}

	public void setCustIdcardNo(String custIdcardNo) {
		this.custIdcardNo = custIdcardNo;
	}

	public String getCustTel() {
		return custTel;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public RoomMaster getRoom() {
		return room;
	}

	public void setRoom(RoomMaster room) {
		this.room = room;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getEstCheckinDate() {
		return estCheckinDate;
	}

	public void setEstCheckinDate(Date estCheckinDate) {
		this.estCheckinDate = estCheckinDate;
	}

	public Double getDeposit() {
		return deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	
}
