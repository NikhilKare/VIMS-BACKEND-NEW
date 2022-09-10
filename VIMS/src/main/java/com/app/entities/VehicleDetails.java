package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"customer","policy"})
@EqualsAndHashCode(of  = "chasisNo")
public class VehicleDetails {
	@Id
	private String chasisNo;
	private String vehicleType;//type=SUV,Haycback,Sedan...
	private LocalDate registrationDate;
	private String vehicleNumber;//MH-15-....
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cust_id")
	private Customer customer;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "policy_id")
	private Policy policy;
	private LocalDate subscriptionDate;
	private LocalDate expiryDate;
	
	
}
