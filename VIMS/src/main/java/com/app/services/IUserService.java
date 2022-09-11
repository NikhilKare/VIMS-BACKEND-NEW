package com.app.services;

import com.app.dto.RoleDTO;
import com.app.dto.UserUpdate;
import com.app.entities.User;

public interface IUserService {

	
	boolean updatePasswrod(UserUpdate user1);
	User save(User user);
	User getById(long userId);
	boolean addRole(RoleDTO role, User u);
	
}
