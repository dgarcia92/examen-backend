package com.examen.backend.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.examen.backend.entity.EmployeeWorkedHours;
import com.examen.backend.entity.Job;
import com.examen.backend.service.EmployeeServiceImp;
import com.examen.backend.service.IEmployeeWorkedHourServiceImp;
import com.examen.backend.service.JobServiceImp;

@RestController
@RequestMapping("employee-worked")
public class EmployeeWorkedController {

	@Autowired
	private EmployeeServiceImp employeService;
	
	
	@Autowired 
	private IEmployeeWorkedHourServiceImp service;
	
	
	@Autowired
	private JobServiceImp jobService;
	
	
	
	/*	Ejercicio 2  */
	@PostMapping
	public ResponseEntity<Map<String, String>[]> add(@RequestBody EmployeeWorkedHours employeeWorked)
	{	
		
		HashMap<String, String> map = new HashMap<>();
	    map.put("id", null);
	    map.put("status", "false");
	    
		Optional<Employee> employee = employeService.findById(employeeWorked.getEmployee_id());
		if(employee.isEmpty())
		{
			 return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		
		
		Employee employeeDb = employee.get();
		List<EmployeeWorkedHours> workedHours = service.findByEmployee(employeeDb);
		
		
	    
	    if(employeeWorked.getWorked_hours() > 20 || employeeWorked.getWorked_date().after(new Date()) || !workedHours.isEmpty())
		{
			 return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		
				
		
		employeeWorked.setEmployee(employeeDb);
		EmployeeWorkedHours employeWorkedHour =  service.save(employeeWorked);
		
		map.put("id", employeWorkedHour.getId().toString());
		map.put("status", "true");
		
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	
	
	/*	Ejercicio 4  */
	@GetMapping("/worked-hours/{id}/{start_date}/{end_date}")
	public ResponseEntity<Map<String, String>> findWorkedHours(@PathVariable("id") Long id, @PathVariable String start_date, @PathVariable String end_date){
		
		HashMap<String, String> map = new HashMap<>();
	    map.put("total_worked_hours", null);
	    map.put("status", "false");
	    
	    
		Optional<Employee> employee = employeService.findById(id);
		if(employee.isEmpty())
		{
			 return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		
		
		Date date1;
		Date date2;
		
		
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
			date2=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
		} catch (ParseException e) {
			 return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		Integer totalHours = service.sumHours(employee.get(), date1, date2);
		
		System.out.println(totalHours.toString() + " <----- ");
		
		 map.put("total_worked_hours", totalHours.toString());
		 map.put("status", "true");
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	
	
	/*	Ejercicio 5  */
	/*	Ejercicio 4  */
	@GetMapping("/payment/{id}/{start_date}/{end_date}")
	public ResponseEntity<Map<String, String>> findWorkedPay(@PathVariable("id") Long id, @PathVariable String start_date, @PathVariable String end_date){
		
		HashMap<String, String> map = new HashMap<>();
	    map.put("payment", null);
	    map.put("status", "false");
	    
	    
		Optional<Employee> employee = employeService.findById(id);
		if(employee.isEmpty())
		{
			 return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		
		Employee employeeDB = employee.get();
		
		Optional<Job> job = jobService.findById(employeeDB.getJob().getId());
		if(employee.isEmpty())
		{
			return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
		}
		
		
		Job jobDB = job.get();
		
		Date date1;
		Date date2;
		
		
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
			date2=new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
		} catch (ParseException e) {
			 return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		Integer totalHours = service.sumHours(employeeDB, date1, date2);
		
		Double payment  = (double) (totalHours  * jobDB.getSalario());
		
		
		 map.put("payment", payment.toString());
		 map.put("status", "true");
		 return new ResponseEntity(map, HttpStatus.OK);
	}
}
