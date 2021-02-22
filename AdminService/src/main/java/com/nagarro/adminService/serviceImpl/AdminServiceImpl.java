package com.nagarro.adminService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagarro.adminService.common.ServiceRequestStatus;
import com.nagarro.adminService.entity.ServiceRequest;
import com.nagarro.adminService.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	Gson gson;
	List<ServiceRequest> serviceRequestList = new ArrayList<>();
	@Override
	public ServiceRequest findService(String serviceId) {
		Optional<ServiceRequest> resultServiceRequest = serviceRequestList.stream()
				.filter(serviceRequest -> serviceRequest.getId().equals(serviceId)).findFirst();
		if (resultServiceRequest.isPresent())
			return resultServiceRequest.get();
		else
			return null;
	}
	@Override
	@JmsListener(destination = "ServiceRequestReceivedEvent")
	public void receiveServiceRequest(String serviceRequest) {
		ServiceRequest serviceRequestObject = gson.fromJson(serviceRequest,ServiceRequest.class);
		serviceRequestObject.setStatusOfRequest(ServiceRequestStatus.PENDING);
		serviceRequestList.add(serviceRequestObject);
	}

	@Override
	@JmsListener(destination = "cancelPendingServiceRequest")
	public void cancelPendingServiceRequest(String serviceRequestId) {
		ServiceRequest serviceRequest = findService(serviceRequestId);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CANCEL);
	}

}
