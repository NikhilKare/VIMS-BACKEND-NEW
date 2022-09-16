package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.UserDto;
import com.app.entities.User;
import com.app.repository.IUserRepository;

@Service 
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IUserRepository userRepo;
	@Autowired
	ModelMapper mapper;
	
	@Override
	public List<UserDto> getAllUsers(){
		List<User> users = userRepo.findAll();
		return users.stream().map(u->mapper.map(u, UserDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public boolean deleteUser(long id) {
		try {
			User u=userRepo.getById(id);
			userRepo.delete(u);
			return true;
		}
		catch (EntityNotFoundException e) {
			return false;
		}
		
	}

}
