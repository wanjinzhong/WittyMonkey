package com.wittymonkey.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by neilw on 2017/4/7.
 */
public class SimpleUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String realName;

    private String staffNo;

    private String idCardNo;

    private String hotelName;

    private Date entryDatetime;

    private String entryUser;

    private List<String> roles = new ArrayList<String>();

    private String tel;

    private String email;

    private Date registDate;

    private Date dimissionDate;

    private String dimissionNote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String loginName) {
        this.staffNo = loginName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Date getEntryDatetime() {
        return entryDatetime;
    }

    public void setEntryDatetime(Date entryDatetime) {
        this.entryDatetime = entryDatetime;
    }

    public String getEntryUser() {
        return entryUser;
    }

    public void setEntryUser(String entryUser) {
        this.entryUser = entryUser;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getDimissionDate() {
        return dimissionDate;
    }

    public void setDimissionDate(Date dimissionDate) {
        this.dimissionDate = dimissionDate;
    }

    public String getDimissionNote() {
        return dimissionNote;
    }

    public void setDimissionNote(String dimissionNote) {
        this.dimissionNote = dimissionNote;
    }
}
