package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 城市
 * @author neilw
 *
 */
@Table(name="city")
@Entity
public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	//市的代码
	@Column(length=10)
	private String code;
	
	@Column(length=50)
	private String name;
	
	@ManyToOne(targetEntity=Province.class)
	@JoinColumn(name="province_code", referencedColumnName="code", columnDefinition="varchar(10)")
	private Province province;

	@OneToMany(targetEntity=Area.class,mappedBy="city")
	private List<Area> areas = new ArrayList<Area>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	


}
