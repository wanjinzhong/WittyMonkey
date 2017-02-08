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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户实体，用于保存用户信息
 * 
 * @author Neil
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;

	@Column(name = "idcard_no")
	private String idCardNo;

	@ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id", referencedColumnName = "id")
	private Hotel hotel;

	// 是否离职
	@Column(name = "is_dimission")
	private Boolean isDimission;

	// 离职原因或说明
	@Column(name = "dimission_desc")
	private String dimissionDesc;

	@Column(name = "entry_datetime")
	private Date entryDatetime;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "entry_id", referencedColumnName = "id")
	private User entryUser;

	@ManyToMany(targetEntity=Role.class)
	@JoinTable(name = "user_role", 
	joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
	inverseJoinColumns={@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private List<Role> roles = new ArrayList<Role>();

	@OneToOne(targetEntity=Setting.class)
	@JoinColumn(name="setting_id", referencedColumnName="id")
	private Setting setting;
	
	
	@Column
	private String tel;

	@Column
	private String email;

	@Column(name = "regist_date")
	private Date registDate;


	public Integer getId() {
		return id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Boolean getIsDimission() {
		return isDimission;
	}

	public void setIsDimission(Boolean isDimission) {
		this.isDimission = isDimission;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDimissionDesc() {
		return dimissionDesc;
	}

	public void setDimissionDesc(String dimissionDesc) {
		this.dimissionDesc = dimissionDesc;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

}
