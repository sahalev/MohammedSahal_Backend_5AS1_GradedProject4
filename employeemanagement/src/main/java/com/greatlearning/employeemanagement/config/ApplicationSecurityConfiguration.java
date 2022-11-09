package com.greatlearning.employeemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.employeemanagement.service.DomainUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  DomainUserDetailsService userDetailsService;

	
	//Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * auth.inMemoryAuthentication() .withUser("sahal")
		 * .password(passwordEncoder().encode("Welcome1$")) .roles("USER") .and()
		 * .withUser("vinay") .password(passwordEncoder().encode("Welcome1$"))
		 * .roles("ADMIN");
		 */
		auth
			.userDetailsService(this.userDetailsService)
			.passwordEncoder(passwordEncoder());
			
	}
	
	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
							.antMatchers(HttpMethod.GET,"/api/employees/**")
							.hasAnyRole("USER","ADMIN")
							.antMatchers("/h2-console/**","/login**")
							.permitAll()
							.and()
							.authorizeRequests()
							.antMatchers(HttpMethod.POST,"/api/employees/**")
							.hasRole("ADMIN")
							.and()
							.authorizeRequests()
							.antMatchers(HttpMethod.POST,"/api/user/**")
							.hasRole("ADMIN")
							.and()
							.authorizeRequests()
							.antMatchers(HttpMethod.PUT,"/api/employees/**")
							.hasRole("ADMIN")
							.and()
							.authorizeRequests()
							.antMatchers(HttpMethod.DELETE,"/api/employees/**")
							.hasRole("ADMIN")
							.anyRequest()
							.authenticated()
							.and()
							.formLogin()
							.and()
							.httpBasic()
							.and()
							.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
							
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	

}
