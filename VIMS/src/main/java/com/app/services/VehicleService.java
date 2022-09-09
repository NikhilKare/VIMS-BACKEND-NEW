package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Customer;
import com.app.entities.VehicleDetails;
import com.app.repository.ICustomerRepository;
import com.app.repository.IVehicleRepository;

@Service
@Transactional
public class VehicleService implements IVehicleService{
	
	@Autowired
	private IVehicleRepository vehicleRepo;
	@Autowired
	private ICustomerRepository custRepo;
	
	@Override
	public boolean addVehicleDetails(VehicleDetails vehicles,long userId) {
		try {
			Customer customer = custRepo.getById(userId);
			vehicles.setCustomer(customer);
			customer.getVehicles().add(vehicles);	
			custRepo.save(customer);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	
	}
}
