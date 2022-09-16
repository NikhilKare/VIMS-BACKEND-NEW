package com.app.services;

import java.util.List;

import com.app.dto.UserDto;

public interface IAdminService {

	List<UserDto> getAllUsers();

	boolean deleteUser(long id);

}
