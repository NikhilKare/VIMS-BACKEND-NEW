package com.app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.RoleDTO;
import com.app.dto.UserDto;
import com.app.dto.UserUpdate;
import com.app.entities.User;

public interface IUserService {

	
	boolean updatePasswrod(UserUpdate user1);
	User save(User user);
	User getById(long userId);
	boolean addRole(RoleDTO role, User u);
	UserDto uploadImage(long id, MultipartFile file) throws IOException;
	byte[] restoreImage(long userId) throws IOException;
	
}
