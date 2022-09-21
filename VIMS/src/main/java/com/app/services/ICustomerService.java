package com.app.services;


import java.util.List;

import com.app.dto.DebitCardDetailsDTO;
import com.app.dto.PaymentDTO;
import com.app.dto.PolicyDetailsDTO;
import com.app.dto.VehicleDTO;

public interface ICustomerService {

//	Customer getCustomerById(long id);

	List<VehicleDTO> getAllVehicles(long custId);

	boolean addVehicleDetails(VehicleDTO vehicles, long userId);
	
	boolean subscribePolicy(String vehicleId, long policyId,long userId);

	boolean deleteVehicle(long id, String chasisNo);

	String getLicenseNo(long id);

	PolicyDetailsDTO getPolicyById(long id);

	PaymentDTO doPayment(long id, long policyId, String chasisNo, DebitCardDetailsDTO cardDto);



}
