package com.examen.backend.service;

import java.util.Date;
import java.util.List;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.EmployeeWorkedHours;

public interface IEmployeeWorkedHourService {

	EmployeeWorkedHours save(EmployeeWorkedHours employeWorked);

	List<EmployeeWorkedHours> findByEmployee(Employee employe);
	
	Integer sumHours(Employee employee, Date worked_date, Date end_date);
}
