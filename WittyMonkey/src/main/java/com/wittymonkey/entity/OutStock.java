package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 出库
 *
 * @author neilw
 */
@Entity
@Table(name = "out_stock")
public class OutStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 出库类型（销售出库/损坏出库/消费出库）
    @Column
    private Integer type;

    @Column
    private Double quantity;

    // 单价
    @Column(name = "price")
    private Double price;

    // 总价
    @Column
    private Double payment;

    @OneToOne(targetEntity = Finance.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "finance_id", referencedColumnName = "id")
    private Finance finance;

    @ManyToOne(targetEntity = Materiel.class)
    @JoinColumn(name = "materiel_id", referencedColumnName = "id")
    private Materiel materiel;

    @ManyToOne(targetEntity = Hotel.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;

    @Column(length = 1024)
    private String note;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }
}
