package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.VehicleDTO;
import com.app.services.ICustomerService;
import com.app.utils.Response;

@RestController
@CrossOrigin
@RequestMapping("/api/customers/{id}")
public class CustomerController {

	@Autowired
	private ICustomerService custServ;
	
	@GetMapping("/policy/{policyid}")
	public ResponseEntity<?> getPolicyById(@PathVariable("id") long custId,@PathVariable long policyid){		
	
		return Response.success(custServ.getPolicyById(policyid));	
	}
	
	@GetMapping
	public String getCustomer(@PathVariable long id){
		return  custServ.getLicenseNo(id);
	}
	
	@PostMapping("/vehicles")
	public ResponseEntity<?> addVehicleDetails(@RequestBody VehicleDTO vehicleDTO,@PathVariable("id") long custId){
		if(custServ.addVehicleDetails(vehicleDTO, custId)) {
			return Response.success("Vehicle Details Added Successfully...");
		}
		return Response.error("Something Went Wrong..."); 
	}
	
	@GetMapping("/vehicles/{vehicleId}/addPolicy")
	public ResponseEntity<?> subscribePolicy(@PathVariable String vehicleId,@RequestParam long policyId,@PathVariable long id){
		
		if(custServ.subscribePolicy(vehicleId,policyId,id)) {
			return Response.success("Policy Subscribed Successfully...");
		}
		return Response.error("Something Went Wrong..."); 		
	}
	
	@GetMapping("/vehicles")
	public ResponseEntity<?> getAllvehicles(@PathVariable("id") long custId){		
	
		return Response.success(custServ.getAllVehicles(custId));	
	}
	
	@DeleteMapping("/vehicles/{vehicleId}")
	public ResponseEntity<?> deleteVehicle(@PathVariable long id,@PathVariable("vehicleId") String chasisNo){
		if(custServ.deleteVehicle(id,chasisNo))
			return Response.success("Vehicle deleted Successfully");
		return Response.error("Something went Wrong");
		
	}
}
