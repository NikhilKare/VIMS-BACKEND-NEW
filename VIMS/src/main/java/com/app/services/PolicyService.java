package com.app.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Customer;
import com.app.entities.Policy;
import com.app.entities.VehicleDetails;
import com.app.repository.ICustomerRepository;
import com.app.repository.IPolicyRepository;
import com.app.repository.IVehicleRepository;

@Service
@Transactional
public class PolicyService implements IPolicyService{
	
	@Autowired
	private IPolicyRepository policyRepo;
	@Autowired
	private IVehicleRepository vehicleRepo;
	@Autowired
	private ICustomerRepository custRepo;
	
	@Override
	public boolean subscribePolicy(String vehicleId,long policyId,long userId) {
		Policy policy = policyRepo.getById(policyId);
		VehicleDetails vehicleDetails = vehicleRepo.getById(vehicleId);
		Customer customer = custRepo.getById(userId);
		if(customer.getVehicles().contains(vehicleDetails)) {
			vehicleDetails.setPolicy(policy);
			vehicleDetails.setSubscriptionDate(LocalDate.now());
			vehicleDetails.setExpiryDate(LocalDate.now().plusYears(policy.getDuration()));
			vehicleRepo.save(vehicleDetails);
			return true;
		}
		return false;		
	}
}
