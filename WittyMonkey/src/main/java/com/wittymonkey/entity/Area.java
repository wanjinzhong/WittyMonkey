package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 地区
 *
 * @author neilw
 */
@Table(name = "area")
@Entity
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 地区编号
    @Column
    private Integer code;

    @Column(length = 50)
    private String name;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_code", referencedColumnName = "code")
    private City city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


}
