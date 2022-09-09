package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.User;
import com.app.entities.VehicleDetails;
import com.app.services.IPolicyService;
import com.app.services.IVehicleService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private IVehicleService vehicleServ;
	@Autowired
	private IPolicyService policyServ;
	
	@PostMapping("/vehicle")
	public ResponseEntity<?> addVehicleDetails(@RequestBody VehicleDetails vehicle,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		if(u!=null && vehicleServ.addVehicleDetails(vehicle, u.getUserId())) {
			return Response.success("Vehicle Details Added Successfully...");
		}
		return Response.error("Something Went Wrong..."); 
	}
	
	@GetMapping
	public ResponseEntity<?> subscribePolicy(@RequestParam String vehicleId,@RequestParam long policyId,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		if(policyServ.subscribePolicy(vehicleId,policyId,u.getUserId())) {
			return Response.success("Policy Subscribed Successfully...");
		}
		return Response.error("Something Went Wrong..."); 		
	}
}
