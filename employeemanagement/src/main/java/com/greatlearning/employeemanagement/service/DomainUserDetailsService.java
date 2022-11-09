package com.greatlearning.employeemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagement.model.DomainUserDetails;
import com.greatlearning.employeemanagement.model.User;
import com.greatlearning.employeemanagement.repository.UserRepository;

@Service
public class DomainUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username).orElseThrow(()->(new UsernameNotFoundException(username+"Not foud")));
		System.out.println("User from the repository");
		System.out.println(user);
		return new DomainUserDetails(user);
	}

}
