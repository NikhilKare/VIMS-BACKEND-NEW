package com.app.services;


import java.util.List;

import com.app.dto.VehicleDTO;
import com.app.entities.Customer;
import com.app.entities.Policy;

public interface ICustomerService {

	Customer getCustomerById(long id);

	List<VehicleDTO> getAllVehicles(long custId);

	boolean addVehicleDetails(VehicleDTO vehicles, long userId);
	
	boolean subscribePolicy(String vehicleId, long policyId,long userId);

	boolean deleteVehicle(long id, String chasisNo);



}
