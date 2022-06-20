package com.examen.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.Job;
import com.examen.backend.repository.IEmployee;

@Service
public class EmployeeServiceImp implements IEmployeService {

	
	@Autowired
	private IEmployee repository;
	
	@Override
	public List<Employee> searchByNameAndLastName(String name, String lastname) {
		
		return repository.searchByNameAndLastName(name, lastname);
	}

	@Override
	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Employee> findByJob(Job job) {
		return repository.findByJob(job);
	}

}
