package com.app.dto;

import com.app.utils.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
	
	private String companyName;
	private long licenceNo;
	private Roles roles;	
}
