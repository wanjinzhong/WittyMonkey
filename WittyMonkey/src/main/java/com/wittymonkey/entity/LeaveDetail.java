package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by neilw on 2017/4/27.
 */
@Entity
@Table(name = "leave_detail")
public class LeaveDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = LeaveHeader.class)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private LeaveHeader leaveHeader;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column
    private Integer days;

    @Column
    private Double deduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LeaveHeader getLeaveHeader() {
        return leaveHeader;
    }

    public void setLeaveHeader(LeaveHeader leaveHeader) {
        this.leaveHeader = leaveHeader;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getDeduct() {
        return deduct;
    }

    public void setDeduct(Double deduct) {
        this.deduct = deduct;
    }
}
