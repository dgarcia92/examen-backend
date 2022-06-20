package com.examen.backend.service;

import java.util.List;
import java.util.Optional;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.Job;


public interface IEmployeService {

	Optional<Employee> findById(Long id);
	
	List<Employee> searchByNameAndLastName(String name, String lastname);
	
	Employee save(Employee employee);
	
	List<Employee> findByJob(Job job);
}
