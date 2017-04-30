package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 保存每个酒店当前员工号
 * Created by neilw on 2017/4/11.
 */
@Table(name = "odom")
@Entity
public class Odom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = Hotel.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "sequence")
    private Integer sequence;

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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
