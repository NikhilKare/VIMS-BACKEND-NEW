package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.VehicleDetails;

public interface IVehicleRepository extends JpaRepository<VehicleDetails, String>{

}
