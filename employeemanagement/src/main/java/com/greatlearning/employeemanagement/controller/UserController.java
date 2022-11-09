package com.greatlearning.employeemanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagement.model.Employee;
import com.greatlearning.employeemanagement.model.User;
import com.greatlearning.employeemanagement.service.EmployeeService;
import com.greatlearning.employeemanagement.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping()
	public String saveUser(@RequestBody User user)
	{
		System.out.println(user);
		return this.service.saveUser(user).toString();
	}

}
