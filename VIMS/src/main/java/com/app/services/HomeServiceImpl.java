package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.UserDto;
import com.app.entities.Policy;
import com.app.entities.User;
import com.app.repository.IPolicyRepository;
import com.app.repository.IUserRepository;
import com.app.utils.StatusEnum;

@Service
@Transactional
public class HomeServiceImpl implements IHomeService {

	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	ModelMapper mapper;
	@Autowired 
	IPolicyRepository policyRepo;
	@Override
	public User findByEmailAndPass(String email,String pass) {
		System.out.println(email+" "+pass);
		return userRepo.findByEmailAndPassword(email, pass);
	}
	
	@Override
	public boolean RegisterUser(UserDto u) {
		try {
		User u1=mapper.map(u, User.class);
		u1.setStatus(StatusEnum.ACTIVE);
		u1.setDateCreated(LocalDate.now());
		userRepo.save(u1);
		return true;
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			return false;
		}
	}
	
	@Override
	public List<PolicyDetailsDTO> getAllPolicies() {
		
		 List<Policy> policies = policyRepo.findAll();
		 List<PolicyDetailsDTO> policiesDTO=new ArrayList<PolicyDetailsDTO>();
		 
		policies.forEach(i-> policiesDTO.add( mapper.map(i, PolicyDetailsDTO.class)));
		return policiesDTO;
		// return policies.stream().map(i->mapper.map(policies, PolicyDetailsDTO.class)).collect(Collectors.toList());
	}
}
