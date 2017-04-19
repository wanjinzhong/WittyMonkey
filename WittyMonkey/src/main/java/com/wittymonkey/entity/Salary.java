package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * 工资
 * @author neilw
 *
 */
@Entity
@Table(name = "salary")
public class Salary implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=User.class)
	@JoinColumn(name="staff_id", referencedColumnName="id")
	private User staff;

	@OneToMany(targetEntity = SalaryRecord.class, mappedBy = "salary", cascade = {CascadeType.ALL})
	private List<SalaryRecord> salaryRecords = new ArrayList<SalaryRecord>();

	@OneToMany(targetEntity = SalaryHistory.class, mappedBy = "salary", cascade = {CascadeType.ALL})
	private List<SalaryHistory> salaryHistories = new ArrayList<SalaryHistory>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getStaff() {
		return staff;
	}

	public void setStaff(User staff) {
		this.staff = staff;
	}

	public List<SalaryRecord> getSalaryRecords() {
		return salaryRecords;
	}

	public void setSalaryRecords(List<SalaryRecord> salaryRecords) {
		this.salaryRecords = salaryRecords;
	}

	public List<SalaryHistory> getSalaryHistories() {
		return salaryHistories;
	}

	public void setSalaryHistories(List<SalaryHistory> salaryHistories) {
		this.salaryHistories = salaryHistories;
	}
}
