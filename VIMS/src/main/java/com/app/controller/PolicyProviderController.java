package com.app.controller;

import java.util.List;

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

import com.app.dto.PolicyDetailsDTO;
import com.app.entities.Policy;
import com.app.entities.User;
import com.app.services.IProviderService;
import com.app.utils.Response;

@RestController
@RequestMapping("/provider")
public class PolicyProviderController {
	
	@Autowired
	private IProviderService providerServ;
	
	@GetMapping("/{id}/policies")
	public ResponseEntity<?> getPoliciesOfProvider(@PathVariable long id)
	{
	List<PolicyDetailsDTO> policies= providerServ.getPolicies(id);
	if(policies.isEmpty())
		return Response.success("No Policies added");
	return Response.success(policies);
	}
	
	
	@PostMapping
	public ResponseEntity<?> addPolicy(@RequestBody PolicyDetailsDTO policyDTO,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		
		if(providerServ.addPolicy(policyDTO, u)) {
			return Response.success("Policy Added Successfully...");
		}
		return Response.error("Failed to add policy...Please check");		
	}
	
	@GetMapping("/policies/{policyId}")
	public ResponseEntity<?> getPolicy(@RequestParam long id,@PathVariable long policyId){
		
		 
		
		return Response.success(providerServ.getPolicy(id,policyId));
		
	}
	
	@DeleteMapping("/{policyId}")
	public ResponseEntity<?> removePolicy(@RequestParam long id,@PathVariable long policyId){
		
		boolean result=providerServ.deletePolicy(id,policyId);
		
		return null;
		
	}
	
}
