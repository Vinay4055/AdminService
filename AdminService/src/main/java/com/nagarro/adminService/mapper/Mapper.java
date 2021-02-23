package com.nagarro.adminService.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.adminService.model.ServiceRequest;


@Component
public class Mapper {
	@Autowired
	ModelMapper modelMapper;

	public ServiceRequest convertServiceRequestEntityToModel(
			com.nagarro.adminService.entity.ServiceRequest source) {
		return modelMapper.map(source, ServiceRequest.class);
	}

	public com.nagarro.adminService.entity.ServiceRequest convertServiceRequestModelToEntity(
			ServiceRequest source) {
		return modelMapper.map(source, com.nagarro.adminService.entity.ServiceRequest.class);
	}

}
