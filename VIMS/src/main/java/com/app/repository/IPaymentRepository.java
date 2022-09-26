package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.app.entities.Payment;
import com.app.entities.VehicleDetails;

public interface IPaymentRepository extends JpaRepository<Payment, Long>{

	void deleteAllByVehicle(VehicleDetails vehicle);

	
}
