package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 报销
 * @author neilw
 *
 */
@Entity
@Table(name = "reimburse")
public class Reimburse implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="apply_user_id", referencedColumnName="id")
	private User applyUser;
	
	@Column
	private Double money;
	
	// 申请状态（待审批/通过/驳回）
	@Column
	private Integer status;
	
	@Column(name="apply_user_note",length=1024)
	private String applyUserNote;

	@Column(name="apply_datetime")
	private Date applyDatetime;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
	@Column(name="entry_user_note", length=1024)
	private String entryUserNote;

	@ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;
	
	@OneToMany(targetEntity=Invoice.class, fetch=FetchType.EAGER)
	@JoinColumn(name="reimburse_id")
	private List<Invoice> invoices = new ArrayList<Invoice>();

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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Date getApplyDatetime() {
		return applyDatetime;
	}

	public void setApplyDatetime(Date applyDatetime) {
		this.applyDatetime = applyDatetime;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
