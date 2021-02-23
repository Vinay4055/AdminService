package com.nagarro.adminService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.adminService.entity.ServiceRequest;
import com.nagarro.adminService.model.ServiceProvider;

@Service
public interface AdminService {
	public ServiceRequest findService(String serviceId);
	public void receiveServiceRequest(String serviceRequest);
	public void cancelPendingServiceRequest(String serviceRequestId);
	public List<ServiceProvider> getServiceProviderListBasedOnLocation(String location);
}
