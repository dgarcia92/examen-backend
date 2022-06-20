package com.examen.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.EmployeeWorkedHours;
import com.examen.backend.repository.IEmployeeWorkedHour;

@Service
public class IEmployeeWorkedHourServiceImp implements IEmployeeWorkedHourService {

	@Autowired
	private IEmployeeWorkedHour repository;
	
	@Override
	public EmployeeWorkedHours save(EmployeeWorkedHours employeWorked) {
		return repository.save(employeWorked);
	}

	@Override
	public List<EmployeeWorkedHours> findByEmployee(Employee employe) {
		return repository.findByEmployee(employe);
	}

	@Override
	public Integer sumHours(Employee employee, Date worked_date, Date end_date) {
		return repository.sumHours(employee, worked_date, end_date);
	}

}
