package com.examen.backend.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(name="last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date birthdate;
	
	@ManyToOne(fetch = FetchType.EAGER )
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="gender_id")
	private Gender gender;
	
	@ManyToOne(fetch = FetchType.LAZY )
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="job_id")
	private Job job;
	
	
	@Transient
	private Long gender_id;
	
	@Transient
	private Long job_id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	

	public Long getGender_id() {
		return gender_id;
	}

	public void setGender_id(Long gender_id) {
		this.gender_id = gender_id;
	}

	public Long getJob_id() {
		return job_id;
	}

	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Employee() {
		
	}


	public Employee(Long id, String name, String lastName, Date birthdate, Gender gender, Job job, Long gender_id,
			Long job_id) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.gender = gender;
		this.job = job;
		this.gender_id = gender_id;
		this.job_id = job_id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", gender=" + gender + ", job=" + job + ", gender_id=" + gender_id + ", job_id=" + job_id + "]";
	}

	
	
	
	public int getEdad()
	{
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		String strDate = format.format(this.birthdate);  
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento = LocalDate.parse(strDate, formatter);
		Period edad = Period.between(fechaNacimiento, LocalDate.now());
		return edad.getYears();
	}
	
}
