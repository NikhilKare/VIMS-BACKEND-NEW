package com.app.services;

import java.util.List;

import com.app.dto.ProviderDTO;
import com.app.dto.UserDto;
import com.app.utils.StatusEnum;

public interface IAdminService {

	List<UserDto> getAllUsers();

	List<ProviderDTO> getAllCompanyProvider(long id);

	boolean changeStatus(long id,StatusEnum status, long providerId);

	boolean deleteProvider(long id, long providerId);

}
