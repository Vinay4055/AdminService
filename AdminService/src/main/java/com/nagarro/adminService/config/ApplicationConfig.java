package com.nagarro.adminService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
@Configuration
public class ApplicationConfig {
	 @Bean(name="gson")
	 	public Gson gson() {
		 return new Gson();
	 }
}
