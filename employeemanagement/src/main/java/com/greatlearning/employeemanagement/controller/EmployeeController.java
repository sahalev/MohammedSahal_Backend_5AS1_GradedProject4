package com.greatlearning.employeemanagement.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagement.model.Employee;
import com.greatlearning.employeemanagement.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")

public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@PostMapping()
	public Employee saveEmployee(@RequestBody Employee emp)
	{
		return this.service.saveEmployee(emp);
	}
	
	@PutMapping("/updateEmployee")
	public Employee updateEmployee(@RequestBody Employee em)
	{
		return this.service.saveEmployee(em);
	}
	
	@GetMapping()
	public Set<Employee> fetchAllEmployees()
	{
		return this.service.fetchAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee fetchEmployeeById(@PathVariable("id") long empId)
	{
		return this.service.fetchEmployeeById(empId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") long empId)
	{
		 this.service.deleteEmployeeById(empId);
	}
	
	@GetMapping("search/{firstName}")
	public Set<Employee> fetchEmployeesByFirstName(@PathVariable("firstName") String firstName)
	{
		return this.service.fetchEmployeesbyFirstName(firstName);
	}
	
	@GetMapping("sort")
	public List<Employee> fetchEmployeesSorted(@RequestParam("order") String order )
	{
		return this.service.fetchEmployeesSortedFirstName(order);
	}
	
	

}
