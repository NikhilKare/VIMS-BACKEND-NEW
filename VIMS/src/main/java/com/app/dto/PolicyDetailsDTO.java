package com.app.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDetailsDTO {

	private Long policyId;
	private String policyName;
	private double policyPremium;
	private String policyType;//type=ThirdPart or Comprehensive
	private LocalDate policyLaunchDate;
	private int duration;
	private ProviderDTO provider;
	
}
