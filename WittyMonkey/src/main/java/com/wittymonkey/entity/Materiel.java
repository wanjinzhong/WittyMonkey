package com.wittymonkey.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 物料
 * @author neilw
 *
 */
@Entity
@Table(name = "material")
public class Materiel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=100)
	private String name;
	
	// 售价
	@Column(name="sell_price")
	private Double sellPrice;

	// 库存
	@Column
	private Double stock; 
	
	// 单位
	@Column(length=10)
	private String unit; 
	
	// 预警库存
	@Column(name="warning_stock")
	private Double warningStock;

	@ManyToOne(targetEntity=MaterielType.class)
	@JoinColumn(name="materiel_type_id", referencedColumnName="id")
	private MaterielType materielType;
	
	@Column(length=1024)
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
	
	
}
