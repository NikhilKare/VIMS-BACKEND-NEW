package com.app.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long policyId;
	private String policyName;
	private double policyPremium;
	private String policyType;//type=ThirdPart or Comprehensive
	private LocalDate policyDate;
	private String policyYears;
	private LocalDate expiryDate;
	
	@OneToMany(mappedBy = "policy",cascade = CascadeType.ALL)
	private Set<VehicleDetails> vehicle=new HashSet<VehicleDetails>();
	@ManyToOne(cascade = CascadeType.ALL)
	private InsuranceProvider provider;
}
