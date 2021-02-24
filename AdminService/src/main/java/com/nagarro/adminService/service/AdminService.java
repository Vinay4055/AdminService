package com.nagarro.adminService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.adminService.entity.ServiceRequest;
import com.nagarro.adminService.model.ServiceProvider;

@Service
public interface AdminService {
	public ServiceRequest findServiceRequest(String serviceRequestId);
	public void receiveServiceRequest(String serviceRequest);
	public void cancelServiceRequest(String serviceRequestId);
	public List<ServiceProvider> getServiceProviderListBasedOnLocation(String location);
}
