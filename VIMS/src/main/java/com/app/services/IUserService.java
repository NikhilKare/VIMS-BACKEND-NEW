package com.app.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.RoleDTO;
import com.app.dto.UserDto;
import com.app.dto.UserUpdate;
import com.app.entities.User;
import com.app.utils.Roles;

public interface IUserService {

	
	boolean updatePasswrod(UserUpdate user1);
	User save(UserDto user);
	UserDto getById(long userId);
	boolean addRole(RoleDTO role, long uid);
	UserDto uploadImage(long id, MultipartFile file) throws IOException;
	byte[] restoreImage(long userId) throws IOException;
	boolean removeRole(Roles role, long id);
	void changeStatusAccount(long id);
	boolean resetPasswrod(Long id, String password);
	List<String> getAllCompanyNames();
}
