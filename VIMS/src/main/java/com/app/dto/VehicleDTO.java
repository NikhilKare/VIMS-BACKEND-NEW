package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class VehicleDTO {

	//@JsonProperty("id")
	@NotBlank(message = "Chassis Number Cannot be Blank")
	private String chasisNo;
	@NotBlank
	private String vehicleType;//type=SUV,Haycback,Sedan...
	private LocalDate registrationDate;
	@NotBlank(message = "Vehicle Number Cannot be Blank")
	private String vehicleNumber;//MH-15-....
	private LocalDate subscriptionDate;
	private LocalDate expiryDate;
	private PolicyDetailsDTO policy;
}
