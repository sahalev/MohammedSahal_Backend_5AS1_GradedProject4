package com.greatlearning.employeemanagement.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	
	public Set<Employee> findByFirstName(String firstName);

}
