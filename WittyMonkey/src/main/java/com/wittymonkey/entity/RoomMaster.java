package com.wittymonkey.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 房间基本信息
 *
 * @author Neil
 */
@Entity
@Table(name = "room_master")
public class RoomMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Floor.class)
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    private Floor floor;

    @Column
    private Double area;

    @Column(length = 10)
    private String number;

    @Column(length = 20)
    private String name;

    @Column
    private Double price;

    // 单人床数量
    @Column(name = "singe_bed_num")
    private Integer singleBedNum;

    // 双人床数量
    @Column(name = "double_bed_num")
    private Integer doubleBedNum;

    // 可入住人数
    @Column(name = "available_num")
    private Integer availableNum;

    // 房间状态(0:空闲，1：预订，2：入住，3：打扫)
    @Column
    private Integer status;

    @OneToOne(targetEntity = RoomExt.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "room_ext_id", referencedColumnName = "id")
    private RoomExt roomExt;

    // 缩略图URL
    @Column
    private String thumbUrl;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

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

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
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

    public RoomExt getRoomExt() {
        return roomExt;
    }

    public void setRoomExt(RoomExt roomExt) {
        this.roomExt = roomExt;
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
}
