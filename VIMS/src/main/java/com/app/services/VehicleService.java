package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Customer;
import com.app.entities.VehicleDetails;
import com.app.repository.ICustomerRepository;
import com.app.repository.IVehicleRepository;

import DTO.VehicleDTO;

@Service
@Transactional
public class VehicleService implements IVehicleService{
	
	@Autowired
	private IVehicleRepository vehicleRepo;
	@Autowired
	private ICustomerRepository custRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public boolean addVehicleDetails(VehicleDTO vehicleDTO,long userId) {
		try {
			VehicleDetails vehicles=mapper.map(vehicleDTO, VehicleDetails.class);
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
