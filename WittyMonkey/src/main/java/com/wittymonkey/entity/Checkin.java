package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * 入住和退房
 *
 * @author neilw
 */
@Entity
@Table(name = "checkin")
public class Checkin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(targetEntity = Customer.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "customer_checkin",
            joinColumns = {@JoinColumn(name = "checkin_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id", referencedColumnName = "id")})
    private List<Customer> customers = new ArrayList<Customer>();

    @ManyToOne(targetEntity = RoomMaster.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomMaster room;

    // 入住时间
    @Column(name = "checkin_date")
    private Date checkinDate;

    // 预计退房时间
    @Column(name = "est_checkout_date")
    private Date estCheckoutDate;

    //  实际退房时间
    @Column(name = "act_checkout_date")
    private Date actCheckoutDate;

    @Column(name = "price")
    private Double price;

    @OneToOne(targetEntity = Finance.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private Finance finance;

    // 押金
    @Column(name = "foregift")
    private Double foregift;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @OneToOne(targetEntity = Reserve.class)
    @JoinColumn(name = "reserve_id", referencedColumnName = "id")
    private Reserve reserve;

    @Column(length = 1024)
    private String note;

    // 车牌号
    @Column(length = 100, name = "plate_no")
    private String plateNo;


    @OneToMany(targetEntity = ChangeRoom.class, mappedBy = "checkin", cascade = {CascadeType.ALL})
    private List<ChangeRoom> changeRooms = new ArrayList<ChangeRoom>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public RoomMaster getRoom() {
        return room;
    }

    public void setRoom(RoomMaster room) {
        this.room = room;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getEstCheckoutDate() {
        return estCheckoutDate;
    }

    public void setEstCheckoutDate(Date estCheckoutDate) {
        this.estCheckoutDate = estCheckoutDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Reserve getReserve() {
        return reserve;
    }

    public void setReserve(Reserve reserve) {
        this.reserve = reserve;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getActCheckoutDate() {
        return actCheckoutDate;
    }

    public void setActCheckoutDate(Date actCheckoutDate) {
        this.actCheckoutDate = actCheckoutDate;
    }

    public Double getForegift() {
        return foregift;
    }

    public void setForegift(Double foregift) {
        this.foregift = foregift;
    }

    public List<ChangeRoom> getChangeRooms() {
        return changeRooms;
    }

    public void setChangeRooms(List<ChangeRoom> changeRooms) {
        this.changeRooms = changeRooms;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }
}
