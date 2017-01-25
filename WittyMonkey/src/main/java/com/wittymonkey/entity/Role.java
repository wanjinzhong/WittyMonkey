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
import javax.persistence.Table;

/**
 * 角色表，用于保存一个酒店内的员工角色，如前台，后勤等。
 * @author Neil
 *
 */
@Entity
@Table
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(targetEntity=Hotel.class, fetch=FetchType.EAGER)
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	@Column
	private String name;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
	@ManyToMany(targetEntity=User.class, mappedBy="roles")
	private List<User> users = new ArrayList<User>();

	@ManyToMany(targetEntity=Permission.class)
	@JoinTable(name = "role_permission", 
	joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
	inverseJoinColumns={@JoinColumn(name = "permission_id", referencedColumnName = "id")})
	private List<Permission> permissions = new ArrayList<Permission>();
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
