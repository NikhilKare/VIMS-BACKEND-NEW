package com.app.services;

import java.util.List;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.RoleDTO;
import com.app.dto.UserDto;
import com.app.dto.UserUpdate;
import com.app.entities.Policy;
import com.app.entities.User;

public interface IUserService {

	User findByEmailAndPass(String email, String pass);
	boolean RegisterUser(UserDto u);
	boolean updatePasswrod(UserUpdate user1);
	User save(User user);
	User getById(long userId);
	boolean addRole(RoleDTO role, User u);
	List<PolicyDetailsDTO> getAllPolicies();
}
