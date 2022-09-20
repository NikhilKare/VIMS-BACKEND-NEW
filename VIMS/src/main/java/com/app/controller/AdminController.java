package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ProviderDTO;
import com.app.services.IAdminService;
import com.app.utils.Response;
import com.app.utils.StatusEnum;

@CrossOrigin
@RestController
@RequestMapping("/api/admin/{id}")
public class AdminController {
	
	@Autowired
	IAdminService adminServ;
	
	@GetMapping
	public ResponseEntity<?> getAllUsers(){
		return Response.success(adminServ.getAllUsers());
	}
	
	@DeleteMapping("/providers/{providerId}")
	public ResponseEntity<?> deleteProvider(@PathVariable long id,@PathVariable long providerId){
		if(adminServ.deleteProvider(id,providerId)) {
			return Response.success("Provider Deleted Successfully");
		}
		return Response.error("Something went Wrong");
	}
	
	@GetMapping("/providers")
	public ResponseEntity<?> getAllCompanyProviders(@PathVariable long id){
		List<ProviderDTO> companyProvider = adminServ.getAllCompanyProvider(id);
		return Response.success(companyProvider);
	}
	
	@PatchMapping("/{providerId}")
	public ResponseEntity<?> changeStatus(@PathVariable long id,@PathVariable long providerId,@RequestParam StatusEnum status){
		
		if(adminServ.changeStatus(id,status, providerId))
			return Response.success("Status Updated"); 
		return Response.error("Something went wrong");
	}
	
	
}
