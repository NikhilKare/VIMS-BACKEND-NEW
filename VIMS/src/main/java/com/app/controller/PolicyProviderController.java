package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Policy;
import com.app.entities.User;
import com.app.services.IProviderService;

import DTO.PolicyDetailsDTO;

@RestController
@RequestMapping("/provider")
public class PolicyProviderController {
	
	@Autowired
	private IProviderService providerServ;
	
	
	@PostMapping()
	public ResponseEntity<?> addPolicy(@RequestBody PolicyDetailsDTO policyDTO,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		
		if(providerServ.addPolicy(policyDTO, u)) {
			return Response.success("Policy Added Successfully...");
		}
		return Response.error("Failed to add policy...Please check");		
	}
}
