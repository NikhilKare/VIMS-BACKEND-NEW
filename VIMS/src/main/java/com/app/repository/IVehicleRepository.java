package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Customer;
import com.app.entities.VehicleDetails;



public interface IVehicleRepository extends JpaRepository<VehicleDetails, String>{

	
 List<VehicleDetails> findAllByCustomer(Customer cust);

}
