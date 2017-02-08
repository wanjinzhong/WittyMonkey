package com.wittymonkey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 * 房间扩展信息
 * @author Neil
 *
 */
@Entity
@Table(name="room_ext")
public class RoomExt implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=RoomMaster.class)
	@JoinColumn(name="room_id", referencedColumnName="id")
	private RoomMaster roomMaster;
	
	@Column(name="has_window")
	private Boolean hasWindow;
	
	@Column(name="has_wifi")
	private Boolean hasWifi;
	
	// 是否 有线网
	@Column(name="has_reticle")
	private Boolean hasReticle;
	
	@Column(name="has_tv")
	private Boolean hasTV;
	
	@Column(name="has_phone")
	private Boolean hasPhone;
	
	@Column(name="has_air_condition")
	private Boolean hasAirCondition;
	
	// 是否有热水壶
	@Column(name="has_kettle")
	private Boolean hasKettle;
	
	// 是否可加床
	@Column(name="extra_bed")
	private Boolean extraBed;
	
	// 早餐数量
	@Column(name="breakfast_num")
	private Integer breakfastNum;
	
	@Column(name="other_facility")
	private String otherFacility;
	
	@Column
	private String note;

	public Integer getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
	
	public RoomMaster getRoomMaster() {
		return roomMaster;
	}

	public void setRoomMaster(RoomMaster roomMaster) {
		this.roomMaster = roomMaster;
	}

	public Boolean getHasWindow() {
		return hasWindow;
	}

	public void setHasWindow(Boolean hasWindow) {
		this.hasWindow = hasWindow;
	}

	public Boolean getHasWifi() {
		return hasWifi;
	}

	public void setHasWifi(Boolean hasWifi) {
		this.hasWifi = hasWifi;
	}

	public Boolean getHasReticle() {
		return hasReticle;
	}

	public void setHasReticle(Boolean hasReticle) {
		this.hasReticle = hasReticle;
	}

	public Boolean getHasTV() {
		return hasTV;
	}

	public void setHasTV(Boolean hasTV) {
		this.hasTV = hasTV;
	}

	public Boolean getHasPhone() {
		return hasPhone;
	}

	public void setHasPhone(Boolean hasPhone) {
		this.hasPhone = hasPhone;
	}

	public Boolean getHasAirCondition() {
		return hasAirCondition;
	}

	public void setHasAirCondition(Boolean hasAirCondition) {
		this.hasAirCondition = hasAirCondition;
	}

	public Boolean getHasKettle() {
		return hasKettle;
	}

	public void setHasKettle(Boolean hasKettle) {
		this.hasKettle = hasKettle;
	}

	public Boolean getExtraBed() {
		return extraBed;
	}

	public void setExtraBed(Boolean extraBed) {
		this.extraBed = extraBed;
	}

	public Integer getBreakfastNum() {
		return breakfastNum;
	}

	public void setBreakfastNum(Integer breakfastNum) {
		this.breakfastNum = breakfastNum;
	}

	public String getOtherFacility() {
		return otherFacility;
	}

	public void setOtherFacility(String otherFacility) {
		this.otherFacility = otherFacility;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
