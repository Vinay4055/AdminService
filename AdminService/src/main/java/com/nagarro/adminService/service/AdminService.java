package com.nagarro.adminService.service;

import org.springframework.stereotype.Service;

import com.nagarro.adminService.entity.ServiceRequest;

@Service
public interface AdminService {
	public ServiceRequest findService(String serviceId);
	public void receiveServiceRequest(String serviceRequest);
	public void cancelPendingServiceRequest(String serviceRequestId);
}
