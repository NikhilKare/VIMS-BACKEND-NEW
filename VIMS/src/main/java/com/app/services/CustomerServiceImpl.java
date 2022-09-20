package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.VehicleDTO;
import com.app.entities.Customer;
import com.app.entities.Policy;
import com.app.entities.VehicleDetails;
import com.app.repository.ICustomerRepository;
import com.app.repository.IPolicyRepository;
import com.app.repository.IVehicleRepository;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

	
	@Autowired
	private IPolicyRepository policyRepo;
	@Autowired
	private IVehicleRepository vehicleRepo;
	
	@Autowired
	ICustomerRepository custRepo;
	
	@Autowired 
	ModelMapper mapper;
	@Override
	public String getLicenseNo(long id) {
		 Customer customer = custRepo.getById(id);
		 return customer.getLicenceNo();
	}
	
	@Override
	public List<VehicleDTO> getAllVehicles(long custId) {
		Customer cust=custRepo.getById(custId);
		List<VehicleDTO> vehicleDTO=new ArrayList<VehicleDTO>();
		cust.getVehicles().forEach(i->vehicleDTO.add(mapper.map(i, VehicleDTO.class)));
		return vehicleDTO;
		
	}
	
	
	
	@Override
	public boolean addVehicleDetails(VehicleDTO vehicleDTO,long userId) {
		try {
			VehicleDetails vehicles=mapper.map(vehicleDTO, VehicleDetails.class);
			Customer customer = custRepo.getById(userId);
			vehicles.setCustomer(customer);
			//customer.getVehicles().add(vehicles);	
			vehicleRepo.save(vehicles);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}	
	}
	
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

	@Override
	public boolean deleteVehicle(long id, String chasisNo) {
		Boolean result=false;
		Customer cust=custRepo.getById(id);
		System.out.println(cust.getVehicles());
		VehicleDetails vehicle=new VehicleDetails();
		vehicle.setChasisNo(chasisNo);
		if(cust.getVehicles().contains(vehicle))
		{
			System.out.println("in if");
			vehicle.setPolicy(null);
			cust.getVehicles().remove(vehicle);
			vehicleRepo.delete(vehicle);
			result=true;
		}
		return result;
	}

	@Override
	public PolicyDetailsDTO getPolicyById(long id) {
		PolicyDetailsDTO policy=mapper.map(policyRepo.findById(id).get(),PolicyDetailsDTO.class);
		return policy;
	}
	
}
