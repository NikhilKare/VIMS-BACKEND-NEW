package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.ProviderDTO;
import com.app.services.IProviderService;
import com.app.utils.Response;

@RestController
@RequestMapping("/api/provider")
@CrossOrigin
public class PolicyProviderController {
	
	@Autowired
	private IProviderService providerServ;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getProvider(@PathVariable long id){
		ProviderDTO provider = providerServ.getProvider(id);
		return Response.success(provider);
	}
	
	@GetMapping("/{id}/policies")
	public ResponseEntity<?> getPoliciesOfProvider(@PathVariable long id,@RequestParam int pageNo)
	{
	List<PolicyDetailsDTO> policies= providerServ.getPolicies(id,pageNo);
	return Response.success(policies);
	}
	
	
	@PostMapping("/{providerId}")
	public ResponseEntity<?> addPolicy(@RequestBody PolicyDetailsDTO policyDTO,@PathVariable long providerId){
	
		if(providerServ.addPolicy(policyDTO, providerId)) {
			return Response.success("Policy Added Successfully...");
		}
		return Response.error("Failed to add policy...Please check");		
	}
	
	@GetMapping("/{id}/policies/{policyId}")
	public ResponseEntity<?> getPolicy(@PathVariable long id,@PathVariable long policyId){
		PolicyDetailsDTO policyDetailsDTO=null;
		 try {
		 policyDetailsDTO = providerServ.getPolicy(id,policyId);
		if (policyDetailsDTO==null)
			return Response.error("Please check policyId");
		 return Response.success(policyDetailsDTO);
		 }catch (Exception e) {
			 return Response.error(e.getMessage());
		}	
	}
	
	@PutMapping("/{providerId}")
	public ResponseEntity<?> updatePolicy(@RequestBody PolicyDetailsDTO policyDTO,@PathVariable long providerId){
	
		if(providerServ.updatePolicy(policyDTO, providerId)) {
			return Response.success("Policy Updated Successfully...");
		}
		return Response.error("Failed to add policy...Please check");		
	}

	
	
	@DeleteMapping("/{id}/policies/{policyId}")
	public ResponseEntity<?> removePolicy(@PathVariable long id,@PathVariable long policyId){
		
		boolean result=providerServ.deletePolicy(id,policyId);
		if(result)
			return Response.success("Policy Deleted Successfully");
		return Response.error("Cannot Remove Policy");
		
		
	}
	
	@GetMapping("/{id}/countPolicy")
	public ResponseEntity<?> getPolicyCount(@PathVariable long id){
		return ResponseEntity.ok(providerServ.getNoOfPolicies(id));
	}
	
}
