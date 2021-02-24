package com.nagarro.adminService.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptServiceRequestResponse implements Serializable{

public AcceptServiceRequestResponse(ServiceProvider serviceProvider, String serviceRequestId) {
		super();
		this.serviceProvider = serviceProvider;
		this.serviceRequestId = serviceRequestId;
	}
ServiceProvider serviceProvider;
String serviceRequestId;
@Override
public String toString() {
	return "AcceptServiceRequestResponse [serviceProvider=" + serviceProvider + ", serviceRequestId=" + serviceRequestId
			+ "]";
}
}
