package com.nagarro.adminService.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nagarro.adminService.common.ServiceRequestStatus;
import com.nagarro.adminService.delegate.AdminServiceDelegate;
import com.nagarro.adminService.entity.ServiceRequest;
import com.nagarro.adminService.mapper.Mapper;
import com.nagarro.adminService.model.NotificationRequest;
import com.nagarro.adminService.model.ServiceProvider;
import com.nagarro.adminService.service.AdminService;
import com.nagarro.adminService.util.Utility;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	Gson gson;
	@Autowired
	AdminServiceDelegate adminServiceDelegate;
	@Autowired
	JmsTemplate jmsTemplate;
	@Autowired
	Mapper mapper;
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
		ServiceRequest serviceRequestObject = gson.fromJson(serviceRequest, ServiceRequest.class);
		serviceRequestObject.setStatusOfRequest(ServiceRequestStatus.PENDING);
		serviceRequestList.add(serviceRequestObject);
		String location = adminServiceDelegate
				.callServiceReceiverAndGetLocation(serviceRequestObject.getEmailIdOfServiceReceiver());
		List<ServiceProvider> serviceProviderList = getServiceProviderListBasedOnLocation(location);
		jmsTemplate.convertAndSend("ServiceRequestReceivedFromAdmin", gson.toJson(new NotificationRequest(serviceProviderList,
				mapper.convertServiceRequestEntityToModel(serviceRequestObject))));
		
	}

	@Override
	@JmsListener(destination = "cancelPendingServiceRequest")
	public void cancelPendingServiceRequest(String serviceRequestId) {
		ServiceRequest serviceRequest = findService(serviceRequestId);
		serviceRequest.setStatusOfRequest(ServiceRequestStatus.CANCEL);
	}

	@Override
	public List<ServiceProvider> getServiceProviderListBasedOnLocation(String location) {
		return adminServiceDelegate.callServiceProviderAndGetListOfServiceProviders(location);

	}

}
