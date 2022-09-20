package com.app.dto;

import com.app.entities.User;
import com.app.utils.StatusEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDTO {
	private long providerId;
	private String companyName;
	private User user;
	private StatusEnum status;
}
