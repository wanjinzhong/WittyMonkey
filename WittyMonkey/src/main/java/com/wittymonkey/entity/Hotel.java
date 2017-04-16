package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 酒店
 *
 * @author Neil
 */
@Table(name = "hotel")
@Entity
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(name = "palce_code")
    private Integer placeCode;

    @Column(name = "place_detail")
    private String placeDetail;

    // 星级
    @Column
    private Integer star;

    @Column(length = 20)
    private String tel;

    @Column(length = 20)
    private String fax;

    @Column(length = 50)
    private String email;

    // 开业时间
    @Column(name = "open_date")
    private Date openDate;

    //加入时间
    @Column(name = "add_date")
    private Date addDate;


    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @OneToOne(targetEntity = Odom.class, mappedBy = "hotel", cascade = {CascadeType.ALL})
    private Odom odom;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @OneToMany(targetEntity = Floor.class, mappedBy = "hotel", fetch = FetchType.EAGER)
    private List<Floor> floors = new ArrayList<Floor>();

    @Column(length = 1024)
    private String note;

    // 法人名字
    @Column(name = "legal_name", length = 20)
    private String legalName;

    // 法人身份证号
    @Column(name = "legal_idcard", length = 18)
    private String legalIdCard;

    // 营业执照编号
    @Column(name = "license_no", length = 15)
    private String licenseNo;

    // 是否停业
    @Column
    private Boolean isClose;

    @OneToMany(targetEntity = MaterielType.class, cascade = {CascadeType.ALL})
    private List<MaterielType> materielTypes = new ArrayList<MaterielType>();

    @OneToMany(targetEntity = LeaveType.class, cascade = {CascadeType.ALL})
    private List<LeaveType> leaveTypes = new ArrayList<LeaveType>();

    @OneToMany(targetEntity = FinanceType.class, cascade = {CascadeType.ALL})
    private List<FinanceType> financeTypes = new ArrayList<FinanceType>();

    public Odom getOdom() {
        return odom;
    }

    public void setOdom(Odom odom) {
        this.odom = odom;
    }

    public Boolean getIsClose() {
        return isClose;
    }

    public void setIsClose(Boolean isClose) {
        this.isClose = isClose;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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


    public Integer getPlaceCode() {
        return placeCode;
    }

    public void setPlaceCode(Integer placeCode) {
        this.placeCode = placeCode;
    }

    public String getPlaceDetail() {
        return placeDetail;
    }

    public void setPlaceDetail(String placeDetail) {
        this.placeDetail = placeDetail;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalIdCard() {
        return legalIdCard;
    }

    public void setLegalIdCard(String legalIdCard) {
        this.legalIdCard = legalIdCard;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public List<MaterielType> getMaterielTypes() {
        return materielTypes;
    }

    public void setMaterielTypes(List<MaterielType> materielTypes) {
        this.materielTypes = materielTypes;
    }

    public List<LeaveType> getLeaveTypes() {
        return leaveTypes;
    }

    public void setLeaveTypes(List<LeaveType> leaveTypes) {
        this.leaveTypes = leaveTypes;
    }

    public Boolean getClose() {
        return isClose;
    }

    public void setClose(Boolean close) {
        isClose = close;
    }

    public List<FinanceType> getFinanceTypes() {
        return financeTypes;
    }

    public void setFinanceTypes(List<FinanceType> financeTypes) {
        this.financeTypes = financeTypes;
    }
}
