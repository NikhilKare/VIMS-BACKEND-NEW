package DTO;

import java.util.HashSet;
import java.util.Set;

import com.app.entities.Roles;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDto {
	
	private Long userId;
	
	private String firstName;
	
	private String lastName;
	
	private String userName;
	private String address;
	private String contactNumber;
	private String email;
	private String password;
	
	private Set<Roles> roles=new HashSet<Roles>();
}
