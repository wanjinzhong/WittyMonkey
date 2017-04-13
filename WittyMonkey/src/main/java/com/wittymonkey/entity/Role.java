package com.wittymonkey.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
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
@Table(name = "role")
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@ManyToOne(targetEntity=Hotel.class, fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	@Column(length=10)
	private String name;
	
	@Column(name="entry_datetime")
	private Date entryDatetime;
	
	@ManyToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinColumn(name="entry_id", referencedColumnName="id")
	private User entryUser;
	
	@ManyToMany(targetEntity=User.class, mappedBy="roles")
	private List<User> users = new ArrayList<User>();

	// 该权限可操作的菜单
	@ManyToMany(targetEntity=Menu.class, fetch = FetchType.EAGER)
	@JoinTable(name = "role_menu", 
	joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") },
	inverseJoinColumns={@JoinColumn(name = "menu_id", referencedColumnName = "id")})
	private List<Menu> menus = new ArrayList<Menu>();
	
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

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * 判断两个角色是否拥有相同菜单
	 * @return
	 */
	public Boolean isMenuSame(Role role){
		List<Integer> thisMenuIds = new ArrayList<Integer>();
		List<Integer> menuIds = new ArrayList<Integer>();
		for(Menu menu: getMenus()){
			thisMenuIds.add(menu.getId());
		}
		for (Menu menu : role.getMenus()){
			menuIds.add(menu.getId());
		}
		if (thisMenuIds.size() != menuIds.size()){
			return false;
		}
		Collections.sort(thisMenuIds);
		Collections.sort(menuIds);
		for (int i = 0; i < thisMenuIds.size(); i ++){
			if (!thisMenuIds.get(i).equals(menuIds.get(i))){
				return false;
			}
		}
		return true;
	}
}
