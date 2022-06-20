package com.examen.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="employee_worked_hours")
public class EmployeeWorkedHours implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private Integer worked_hours;
	
	private Date worked_date;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	
	@Transient
	private Long employee_id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Integer getWorked_hours() {
		return worked_hours;
	}

	public void setWorked_hours(Integer worked_hours) {
		this.worked_hours = worked_hours;
	}

	public Date getWorked_date() {
		return worked_date;
	}

	public void setWorked_date(Date worked_date) {
		this.worked_date = worked_date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public EmployeeWorkedHours(Long id, Integer worked_hours, Date worked_date, Employee employee, Long employee_id) {
		super();
		this.id = id;
		this.worked_hours = worked_hours;
		this.worked_date = worked_date;
		this.employee = employee;
		this.employee_id = employee_id;
	}

	public EmployeeWorkedHours() {
	}

	
	
}
