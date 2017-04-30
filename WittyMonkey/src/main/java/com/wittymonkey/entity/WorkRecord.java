package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 工作记录。记录员工离职信息
 *
 * @author neilw
 */
@Entity
@Table(name = "work_record")
public class WorkRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "real_name", length = 20)
    private String realName;

    @Column(name = "idcard_no", length = 20)
    private String idCardNo;

    @ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    // 离职原因或说明
    @Column(name = "dimission_desc", length = 1024)
    private String dimissionDesc;

    // 离职日期
    @Column(name = "dimission_datetime")
    private Date dimissionDatetime;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "workrecord_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<Role>();

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

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getDimissionDesc() {
        return dimissionDesc;
    }

    public void setDimissionDesc(String dimissionDesc) {
        this.dimissionDesc = dimissionDesc;
    }

    public Date getDimissionDatetime() {
        return dimissionDatetime;
    }

    public void setDimissionDatetime(Date dimissionDatetime) {
        this.dimissionDatetime = dimissionDatetime;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
