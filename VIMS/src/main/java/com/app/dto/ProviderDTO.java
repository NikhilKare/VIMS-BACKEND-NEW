package com.app.dto;

import javax.validation.constraints.NotBlank;

import com.app.entities.User;
import com.app.utils.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDTO {
	private long providerId;
	@NotBlank(message = "Company name must be supplied")
	private String companyName;
	private UserDto user;
	private StatusEnum status;
}
