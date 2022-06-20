package com.examen.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.Job;

@Repository
public interface IEmployee extends JpaRepository<Employee, Long> {
	
	List<Employee> searchByNameAndLastName(String name, String lastname);

	List<Employee> findByJob(Job job);
}
