package com.learn2code.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
	    http
	    .authorizeRequests()
	    .antMatchers("/", "/students", "/mentor").permitAll().anyRequest()
	    .authenticated()
	    .and()
	    .csrf().disable();
	
	}
}
	