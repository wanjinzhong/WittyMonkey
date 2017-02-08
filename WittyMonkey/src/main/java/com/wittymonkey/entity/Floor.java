package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity
@Table
public class Floor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity=Hotel.class,fetch=FetchType.EAGER)
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	@Column
	private Integer floor_no;
	
	@OneToMany(targetEntity=RoomMaster.class,mappedBy="floor")
	private List<RoomMaster> roomMasters = new ArrayList<RoomMaster>();

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

	public Integer getFloor_no() {
		return floor_no;
	}

	public void setFloor_no(Integer floor_no) {
		this.floor_no = floor_no;
	}

	public List<RoomMaster> getRoomMasters() {
		return roomMasters;
	}

	public void setRoomMasters(List<RoomMaster> roomMasters) {
		this.roomMasters = roomMasters;
	}
	
	
}
