package com.app.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.app.utils.Roles;
import com.app.utils.StatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UserDto {
	private Long userId;
	@NotEmpty(message = "First name must be supplied")
	@Length(min = 4,max=30,message = "Invalid First name length")
	private String firstName;
	@NotEmpty(message = "Last name must be supplied")
	private String lastName;
	@NotEmpty(message = "User Name must be supplied")
	private String userName;
	private String address;
	@Pattern(regexp="^\\d{10}$")	
	private String contactNumber;
	@NotBlank
	@Email(message = "Invalid Email")
	private String email;
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String imagePath;
	private StatusEnum status;
	private Set<Roles> roles=new HashSet<Roles>();
}
