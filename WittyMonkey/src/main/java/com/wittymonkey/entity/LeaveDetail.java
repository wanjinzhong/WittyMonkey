package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by neilw on 2017/4/27.
 */
@Entity
@Table(name = "leave_detail")
public class LeaveDetail implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = LeaveHeader.class)
    @JoinColumn(name = "header_id", referencedColumnName = "id")
    private LeaveHeader leaveHeader;

    @Column(name = "from_date")
    private Date from;

    @Column(name = "to_date")
    private Date to;

    @Column
    private Double days;

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

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public Double getDeduct() {
        return deduct;
    }

    public void setDeduct(Double deduct) {
        this.deduct = deduct;
    }
}
