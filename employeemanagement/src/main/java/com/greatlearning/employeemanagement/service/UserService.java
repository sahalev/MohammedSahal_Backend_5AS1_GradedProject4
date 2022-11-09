package com.greatlearning.employeemanagement.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.model.Employee;
import com.greatlearning.employeemanagement.model.Role;
import com.greatlearning.employeemanagement.model.User;
import com.greatlearning.employeemanagement.repository.EmployeeRepository;
import com.greatlearning.employeemanagement.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder passwordencoder;
	
	public User saveUser(User user)
	{
		User user1 = new User();
		user1.setUserName(user.getUserName());
		user1.setPassword(passwordencoder.encode(user.getPassword()));
		user1.setEmailAddress(user.getEmailAddress());
		
		for(Role r :user.getRoles())
		{
			 Role userrole = new Role();
			 userrole.setName(r.getName());
			 user1.addRole(userrole);
		}
		
		System.out.println(user1.getRoles().size());
		
		
		
		return this.userRepository.save(user1);
		
		//return new User();
	}

}
