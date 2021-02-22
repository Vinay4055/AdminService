package com.nagarro.adminService.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.adminService.model.ServiceRequest;

@RestController
@RequestMapping("/admin")
public class AdminServiceController {
	@PostMapping("/")
	public void receiveRequest(@RequestBody @Valid ServiceRequest serviceRequest, BindingResult bindingResult) {
		System.out.println(serviceRequest);
	}

}
