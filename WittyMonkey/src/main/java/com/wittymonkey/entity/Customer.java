package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Neil on 2017/2/22.
 */
@Entity
@Table
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 20)
    private String name;

    @Column(length = 18)
    private String idCard;

    @Column(length = 20)
    private String tel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) obj;
        if (customer.getId() == id || customer.getIdCard() == idCard) {
            return true;
        } else {
            return false;
        }
    }
}
