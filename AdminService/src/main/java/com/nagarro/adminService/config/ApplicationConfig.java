package com.nagarro.adminService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
@Configuration
public class ApplicationConfig {
	 @Bean(name="gson")
	 	public Gson gson() {
		 return new Gson();
	 }
	 @Bean
	   public ModelMapper modelMapper() {
		 return new ModelMapper();
	       }	 
}
