package com.wittymonkey.entity;

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
public class Floor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(targetEntity=Hotel.class,fetch=FetchType.EAGER)
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	private Hotel hotel;
	
	@Column
	private int floor_no;
	
	@OneToMany(targetEntity=RoomMaster.class,mappedBy="floor")
	private List<RoomMaster> roomMasters = new ArrayList<RoomMaster>();
	
}
