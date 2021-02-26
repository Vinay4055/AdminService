package com.nagarro.adminService.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.nagarro.adminService.model.ServiceProvider;

@Component
public class AdminServiceDelegate {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	@Qualifier("gson")
	Gson gson;
	String gatewayServiceUrl = "http://api-gateway:9999/";
	String gatewayUrl = "http://localhost:9999/";
	String url = gatewayUrl;
	public String callServiceReceiverAndGetLocation(String email) {
		String response = restTemplate
				.exchange(url+"serviceReceiver/accountDetail/location/{email}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, email).getBody();
		String location = gson.fromJson(response,String.class);	
		System.out.println("Location = "+location);
		return location;
	}
	
	public List<ServiceProvider> callServiceProviderAndGetListOfServiceProviders(String location) {
		String response = restTemplate
				.exchange(url+"serviceProvider/{location}"
				, HttpMethod.GET
				, null
				, new ParameterizedTypeReference<String>() {
			}, location).getBody();
		 ServiceProvider[] serviceProviderArray= gson.fromJson(response,ServiceProvider[].class);	
		System.out.println("ServiceProviderListBasedOnLocation = "+ Arrays.asList(serviceProviderArray));
		 return Arrays.asList(serviceProviderArray);
		
		
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
