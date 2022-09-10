package com.app.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class VehicleDetails {
	@Id
	private String chasisNo;
	private String vehicleType;//type=SUV,Haycback,Sedan...
	private LocalDate registrationDate;
	private String vehicleNumber;//MH-15-....
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_id")
	private Customer customer;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "policy_id")
	private Policy policy;
	private LocalDate subscriptionDate;
	private LocalDate expiryDate;
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof VehicleDetails) {
			return this.chasisNo.equals(((VehicleDetails)o).getChasisNo());
		}
		return false;
	}
}
