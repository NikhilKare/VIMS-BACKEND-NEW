package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.VehicleDTO;
import com.app.entities.User;
import com.app.services.ICustomerService;
import com.app.utils.Response;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService custServ;
	
	
	
	
	@PostMapping
	public ResponseEntity<?> addVehicleDetails(@RequestBody VehicleDTO vehicleDTO,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		if(u!=null && custServ.addVehicleDetails(vehicleDTO, u.getUserId())) {
			return Response.success("Vehicle Details Added Successfully...");
		}
		return Response.error("Something Went Wrong..."); 
	}
	
	@GetMapping("/addPolicy")
	public ResponseEntity<?> subscribePolicy(@RequestParam String vehicleId,@RequestParam long policyId,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		if(custServ.subscribePolicy(vehicleId,policyId,u.getUserId())) {
			return Response.success("Policy Subscribed Successfully...");
		}
		return Response.error("Something Went Wrong..."); 		
	}
	
	@GetMapping
	public ResponseEntity<?> getAllvehicles(@RequestParam long custId){		
	
		return Response.success(custServ.getAllVehicles(custId));	
	}
	
	@DeleteMapping("/{id}/vehicles/{vehicleId}")
	public ResponseEntity<?> deleteVehicle(@PathVariable long id,@PathVariable("vehicleId") String chasisNo){
		if(custServ.deleteVehicle(id,chasisNo))
			return Response.success("Vehicle deleted Successfully");
		return Response.error("Something went Wrong");
		
	}
}
