package com.app.services;

import com.app.entities.User;

import DTO.RoleDTO;
import DTO.UserDto;
import DTO.UserUpdate;

public interface IUserService {

	User findByEmailAndPass(String email, String pass);
	boolean RegisterUser(UserDto u);
	boolean updatePasswrod(UserUpdate user1);
	User save(User user);
	User getById(long userId);
	boolean addRole(RoleDTO role, User u);
}
