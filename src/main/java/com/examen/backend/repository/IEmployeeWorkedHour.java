package com.examen.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.EmployeeWorkedHours;

@Repository
public interface IEmployeeWorkedHour extends JpaRepository<EmployeeWorkedHours, Long> {

	List<EmployeeWorkedHours> findByEmployee(Employee employee);
	
	
	@Query(value = "SELECT sum(worked_hours) AS worked_hours FROM EmployeeWorkedHours WHERE employee = ?1 AND worked_date >=?2 AND worked_date <=?3")
    public Integer sumHours(Employee employee, Date worked_date, Date end_date);

}
