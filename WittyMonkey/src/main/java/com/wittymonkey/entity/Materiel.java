package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 物料
 *
 * @author neilw
 */
@Entity
@Table(name = "materiel")
public class Materiel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    // 条码号
    @Column(length = 50)
    private String barcode;

    @Column(length = 100)
    private String name;

    // 售价
    @Column(name = "sell_price")
    private Double sellPrice;

    // 库存
    @Column
    private Double stock;

    // 单位
    @Column(length = 10)
    private String unit;

    // 预警库存
    @Column(name = "warning_stock")
    private Double warningStock;

    @ManyToOne(targetEntity = MaterielType.class)
    @JoinColumn(name = "materiel_type_id", referencedColumnName = "id")
    private MaterielType materielType;

    @Column(length = 1024)
    private String note;

    @Column(name = "entry_datetime")
    private Date entryDatetime;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", referencedColumnName = "id")
    private User entryUser;


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

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getWarningStock() {
        return warningStock;
    }

    public void setWarningStock(Double warningStock) {
        this.warningStock = warningStock;
    }

    public MaterielType getMaterielType() {
        return materielType;
    }

    public void setMaterielType(MaterielType materielType) {
        this.materielType = materielType;
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
