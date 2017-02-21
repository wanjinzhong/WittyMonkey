package com.wittymonkey.vo;

import com.wittymonkey.entity.Floor;
import com.wittymonkey.entity.RoomExt;
import com.wittymonkey.entity.RoomMaster;
import com.wittymonkey.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by neilw on 2017/2/21.
 */
public class SimpleRoom {
    private Integer id;

    private Integer floorNo;

    private Double area;

    private String number;

    private String name;

    private Double price;

    private Integer singleBedNum;

    private Integer doubleBedNum;

    private Integer availableNum;

    private Integer status;

    private String thumbUrl;

    private Date entryDatetime;

    private String userName;

    private Boolean hasWindow;

    private Boolean hasWifi;

    private Boolean hasReticle;

    private Boolean hasTV;

    private Boolean hasPhone;

    private Boolean hasAirCondition;

    private Boolean hasKettle;

    private Boolean extraBed;

    private String otherFacility;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(Integer floorNo) {
        this.floorNo = floorNo;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSingleBedNum() {
        return singleBedNum;
    }

    public void setSingleBedNum(Integer singleBedNum) {
        this.singleBedNum = singleBedNum;
    }

    public Integer getDoubleBedNum() {
        return doubleBedNum;
    }

    public void setDoubleBedNum(Integer doubleBedNum) {
        this.doubleBedNum = doubleBedNum;
    }

    public Integer getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(Integer availableNum) {
        this.availableNum = availableNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
