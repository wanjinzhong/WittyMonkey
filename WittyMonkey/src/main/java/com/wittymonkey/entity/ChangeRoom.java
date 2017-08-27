package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 换房
 *
 * @author neilw
 */
@Entity
@Table(name = "change_room")
public class ChangeRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = Checkin.class)
    @JoinColumn(name = "checkin_id", referencedColumnName = "id")
    private Checkin checkin;

    @ManyToOne(targetEntity = RoomMaster.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "from_room_id", referencedColumnName = "id")
    private RoomMaster fromRoom;

    @ManyToOne(targetEntity = RoomMaster.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "to_room_id", referencedColumnName = "id")
    private RoomMaster toRoom;

    // 差价（正数表示补，负数表示退）
    @Column(name = "price_difference")
    private Double priceDifference;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @Column(length = 1024)
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Checkin getCheckin() {
        return checkin;
    }

    public void setCheckin(Checkin checkin) {
        this.checkin = checkin;
    }

    public RoomMaster getFromRoom() {
        return fromRoom;
    }

    public void setFromRoom(RoomMaster fromRoom) {
        this.fromRoom = fromRoom;
    }

    public RoomMaster getToRoom() {
        return toRoom;
    }

    public void setToRoom(RoomMaster toRoom) {
        this.toRoom = toRoom;
    }

    public Double getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(Double priceDifference) {
        this.priceDifference = priceDifference;
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


}
