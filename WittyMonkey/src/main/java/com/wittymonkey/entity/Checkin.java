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
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 入住和退房
 * @author neilw
 *
 */
@Entity
@Table
public class Checkin implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="cust_name", length=20)
	private String custName;
	
	@Column(name="cust_idcard_no", length=20)
	private String custIdcardNo;
	
	@Column(name="cust_tel")
	private String custTel;
	
	@ManyToOne(targetEntity=RoomMaster.class)
	@JoinColumn(name="room_id", referencedColumnName="id")
	private RoomMaster room;

	// 入住时间
	@Column(name="checkin_date")
	private Date checkinDate;
	
	// 预计退房时间
	@Column(name="est_checkout_date")
	private Date estCheckoutDate;
	
	//  实际退房时间
	@Column(name="act_checkout_date")
	private Date actCheckoutDate;
		
	// 差价（正数表示补，负数表示退）
	@Column(name="price_difference")
	private Double priceDifference;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
	@OneToOne(targetEntity=Reserve.class)
	@JoinColumn(name="reserve_id", referencedColumnName="id")
	private Reserve reserve;
	
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

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public RoomMaster getRoom() {
		return room;
	}

	public void setRoom(RoomMaster room) {
		this.room = room;
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

	public Date getEstCheckoutDate() {
		return estCheckoutDate;
	}

	public void setEstCheckoutDate(Date estCheckoutDate) {
		this.estCheckoutDate = estCheckoutDate;
	}

	public Date getActCheckoutDate() {
		return actCheckoutDate;
	}

	public void setActCheckoutDate(Date actCheckoutDate) {
		this.actCheckoutDate = actCheckoutDate;
	}

	public Double getPriceDifference() {
		return priceDifference;
	}

	public void setPriceDifference(Double priceDifference) {
		this.priceDifference = priceDifference;
	}
	
	
}
