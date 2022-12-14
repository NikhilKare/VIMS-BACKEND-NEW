package com.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(exclude = "vehicles")
public class Customer{
	@Id
	private long custId;
	@OneToOne
	@JoinColumn(name = "user_Id")
	@MapsId
	private User user;
	private String licenceNo;
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private Set<VehicleDetails> vehicles=new HashSet<VehicleDetails>();
}