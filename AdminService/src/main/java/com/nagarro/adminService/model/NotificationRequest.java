package com.nagarro.adminService.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class NotificationRequest implements Serializable{
	
	public NotificationRequest(List<ServiceProvider> serviceProviderList, ServiceRequest serviceRequest) {
		super();
		this.serviceProviderList = serviceProviderList;
		this.serviceRequest = serviceRequest;
	}
	List<ServiceProvider> serviceProviderList;
	ServiceRequest serviceRequest;
}
