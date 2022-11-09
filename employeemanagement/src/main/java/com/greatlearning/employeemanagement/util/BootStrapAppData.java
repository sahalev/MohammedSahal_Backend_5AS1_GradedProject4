package com.greatlearning.employeemanagement.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeemanagement.model.Employee;
import com.greatlearning.employeemanagement.model.Role;
import com.greatlearning.employeemanagement.model.User;
import com.greatlearning.employeemanagement.repository.EmployeeRepository;
import com.greatlearning.employeemanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BootStrapAppData {

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	private final PasswordEncoder passwordencoder;
	
	private final UserRepository userRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void insertEmployees(ApplicationReadyEvent event)
	{
		
		  Employee ramesh = new Employee();
		  ramesh.setFirstName("ramesh");
		  ramesh.setLastName("Sharma");
		  ramesh.setEmail("ramesh@gmail.com");
		  employeeRepository.save(ramesh);
		 
		  Employee hanan = new Employee();
		  hanan.setFirstName("hanan");
		  hanan.setLastName("hanan");
		  hanan.setEmail("hanan@gmail.com");
		  employeeRepository.save(hanan);
		  
		  User vinay = new User(); 
		  vinay.setUserName("vinay");
		  vinay.setPassword(passwordencoder.encode("welcome"));
		  vinay.setEmailAddress("vinay@gmail.com");
		  
		  
		  Role userrole = new Role();
		  userrole.setName("USER");
		  
		  Role adminrole = new Role();
		  adminrole.setName("ADMIN");
		  
		//  userrole.setUser(vinay);
		  //adminrole.setUser(vinay);
		  
		 vinay.addRole(adminrole);
		 vinay.addRole(userrole);
		 
		 
		 User sahal = new User();
		  sahal.setUserName("sahal");
		  sahal.setPassword(passwordencoder.encode("welcome"));
		  sahal.setEmailAddress("sahal@gmail.com");
		  sahal.addRole(userrole);
		
		  this.userRepository.save(sahal);
		  this.userRepository.save(vinay);
		 
		
	}
	

	
}
