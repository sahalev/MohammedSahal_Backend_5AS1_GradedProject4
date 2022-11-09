package com.greatlearning.employeemanagement.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.model.Employee;
import com.greatlearning.employeemanagement.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {


	private final EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee emp)
	{
		return this.employeeRepository.save(emp);
	}
	
	
	public Set<Employee> fetchAllEmployees()
	{
		return new HashSet<>(this.employeeRepository.findAll()				);
	}
	
	public Employee fetchEmployeeById(long empid)
	{
		return this.employeeRepository.findById(empid).orElseThrow();
	}
	public void deleteEmployeeById(long empId)
	{
		this.employeeRepository.deleteById(empId);
	}
	
	public Set<Employee> fetchEmployeesbyFirstName(String firstName)
	{
		return new HashSet<>(this.employeeRepository.findByFirstName(firstName));
	}
	
	public List<Employee> fetchEmployeesSortedFirstName(String order)
	{
		List<Employee> allEmployeeList =this.employeeRepository.findAll();
		if(order.equalsIgnoreCase("asc"))
		{
		Collections.sort(allEmployeeList, new sortEmployeesAsc());
		System.out.println("Ascending order");
		}
		else if (order.equalsIgnoreCase("desc"))
		{
		Collections.sort(allEmployeeList, new sortEmployeesDesc());	
		System.out.println("Descending order");
		}
		return allEmployeeList;
		
	}
	
	public static class sortEmployeesAsc implements Comparator<Employee>{

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return (o1.getFirstName().compareTo(o2.getFirstName()));
		}	
	}
	
	public static class sortEmployeesDesc implements Comparator<Employee>{

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return (o2.getFirstName().compareTo(o1.getFirstName()));
		}	
	}

	
}
