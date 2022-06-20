package com.examen.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examen.backend.entity.Employee;
import com.examen.backend.entity.Gender;
import com.examen.backend.entity.Job;
import com.examen.backend.service.EmployeeServiceImp;
import com.examen.backend.service.GenderServiceImp;
import com.examen.backend.service.JobServiceImp;

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImp service;
	
	@Autowired
	private JobServiceImp jobService;
	
	@Autowired
	private GenderServiceImp genderService;
	
	/*	Ejercicio 1  */
	@PostMapping
	public ResponseEntity<Map<String, String>[]> add(@RequestBody Employee employee)
	{
		
		List<Employee> employeDB = service.searchByNameAndLastName(employee.getName(), employee.getLastName());
		Optional<Job> job = jobService.findById(employee.getJob_id());
		Optional<Gender> gender = genderService.findById(employee.getGender_id());
		
		HashMap<String, String> map = new HashMap<>();
	    map.put("id", null);
	    map.put("status", "false");
		
		if(!employeDB.isEmpty() || employee.getEdad() < 18 || job.isEmpty() || gender.isEmpty())
		{	
		    return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}	
		
		Employee newEmploye = service.save(employee);
		
		map.put("id", newEmploye.getId().toString());
		map.put("status", "true");
		
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	
	
	/*	Ejercicio 3  */
	@GetMapping("/{id}")
	public ResponseEntity<Map<List<Employee>, Boolean>> finByJob(@PathVariable("id") Long id){
		
		Optional<Job> job = jobService.findById(id);
		HashMap<String, String> map = new HashMap<>();
	    map.put("id", null);
	    map.put("status", "false");
	    
	    if(job.isEmpty())
	    {
	    	return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
	    }
		
		Job jobDb = job.get();
		
		List<Employee> employees = service.findByJob(jobDb);
		
		return  new ResponseEntity(employees, HttpStatus.OK);
	}
	
	
	
}
