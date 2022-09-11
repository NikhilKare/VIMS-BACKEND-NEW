package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VehicleDTO {

	
	private String chasisNo;
	private String vehicleType;//type=SUV,Haycback,Sedan...
	private LocalDate registrationDate;
	private String vehicleNumber;//MH-15-....
	private LocalDate subscriptionDate;
	private LocalDate expiryDate;
	private PolicyDetailsDTO policy;
}
