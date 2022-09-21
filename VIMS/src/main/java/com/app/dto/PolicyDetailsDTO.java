package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyDetailsDTO {
	@JsonProperty("id")
	private Long policyId;
	@NotEmpty(message = "Policy name must be supplied")
	@Length(min = 4,max=30,message = "Invalid Policy name length")
	private String policyName;
	@NotEmpty
	private double policyPremium;
	@NotEmpty
	private String policyType;//type=ThirdPart or Comprehensive
	private LocalDate policyLaunchDate;
	@NotEmpty
	private int duration;
	private ProviderDTO provider;
	
}
