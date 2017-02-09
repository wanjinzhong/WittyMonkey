package com.wittymonkey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 请假类型
 * @author neilw
 *
 */
@Entity
@Table(name="leave_type")
public class LeaveType implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=20)
	private String name;
	
	@Column(name="total_time")
	private Double totalTime;
	
	@ManyToOne(targetEntity=Hotel.class)
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	// 扣薪额度（按一天工资的百分比）
	@Column
	private Double deduct;
	
	@Column(length=1024)
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Double getDeduct() {
		return deduct;
	}

	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	} 
	
	
}
