package com.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {
	private String email;
	private String oldPass;
	private String newPass;
}
