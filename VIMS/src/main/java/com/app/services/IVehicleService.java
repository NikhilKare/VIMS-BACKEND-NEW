package com.app.services;

import com.app.entities.VehicleDetails;

import DTO.VehicleDTO;

public interface IVehicleService {

	boolean addVehicleDetails(VehicleDTO vehicles, long userId);

}
