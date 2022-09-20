package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.Mapping;

import com.app.utils.Roles;
import com.app.utils.StatusEnum;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UserDto {
	
	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	private String address;
	private String contactNumber;
	private String email;
	private String password;
	private String imagePath;
	private StatusEnum status;
	private Set<Roles> roles=new HashSet<Roles>();
}
