package com.wittymonkey.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 销售
 *
 * @author neilw
 */
@Entity
@Table(name = "sell")
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(targetEntity = OutStock.class)
    @JoinColumn(name = "out_stock_id", referencedColumnName = "id")
    private OutStock outStock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OutStock getOutStock() {
        return outStock;
    }

    public void setOutStock(OutStock outStock) {
        this.outStock = outStock;
    }


}
