package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 财务类型
 * @author neilw
 *
 */
@Entity
@Table(name="finance_type")
public class FinanceType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	// 收/支
	@Column
	private Integer type;
	
	@Column(length=20)
	private String name;
	
	@ManyToOne(targetEntity=Hotel.class)
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	@OneToMany(targetEntity= Finance.class, mappedBy="financeType")
	private List<Finance> finances = new ArrayList<Finance>();

	@Column(length=1024)
	private String note;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public List<Finance> getFinances() {
		return finances;
	}

	public void setFinances(List<Finance> finances) {
		this.finances = finances;
	}
	
	
}
