package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 酒店实体类
 * @author Neil
 *
 */
@Table(name="hotel")
@Entity
public class Hotel implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column(name="city_id")
	private int city;
	
	@Column
	private String place;
	
	// 星级
	@Column
	private int star;
	
	@Column
	private String tel;
	
	@Column
	private String fax;
	
	@Column(name="email")
	private String Email;
	
	// 开业时间
	@Column(name="open_date")
	private Date openDate;
	
	//加入时间
	@Column(name="add_date")
	private Date addDate;
	
	//更新时间
	@Column(name="update_date")
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", city=" + city
				+ ", place=" + place + ", star=" + star + ", tel=" + tel
				+ ", fax=" + fax + ", Email=" + Email + ", openDate="
				+ openDate + ", addDate=" + addDate + ", updateDate="
				+ updateDate + "]";
	}
	
	
}
