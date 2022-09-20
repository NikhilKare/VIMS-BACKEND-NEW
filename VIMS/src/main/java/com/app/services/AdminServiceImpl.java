package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.ProviderDTO;
import com.app.dto.UserDto;
import com.app.entities.InsuranceProvider;
import com.app.entities.User;
import com.app.repository.IProviderRepository;
import com.app.repository.IUserRepository;
import com.app.utils.Roles;
import com.app.utils.StatusEnum;

@Service 
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IUserRepository userRepo;
	@Autowired
	IProviderRepository providerRepo;
	@Autowired
	ModelMapper mapper;
	@Autowired
	IUserService userServ;
	
	@Override
	public List<UserDto> getAllUsers(){
		List<User> users = userRepo.findAll();
		return users.stream().map(u->mapper.map(u, UserDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public boolean deleteProvider(long id,long providerId) {
		try {
			InsuranceProvider admin=providerRepo.getById(id);
			InsuranceProvider p=providerRepo.getById(providerId);
			if(admin.getCompanyName().equals(p.getCompanyName())) {
				userServ.removeRole(Roles.POLICY_PROVIDER, providerId);
//				providerRepo.delete(p);
				
				return true;
			}
		}
		catch (EntityNotFoundException e) {
			return false;
		}
		return false;
		
	}

	@Override
	public List<ProviderDTO> getAllCompanyProvider(long id) {
		InsuranceProvider provider=providerRepo.getById(id);
		List<InsuranceProvider> providerList=providerRepo.findAllByCompanyName(provider.getCompanyName());		
		return providerList.stream().map(e->mapper.map(e, ProviderDTO.class)).collect(Collectors.toList());
	}
	
	@Override
	public boolean changeStatus(long id,StatusEnum status,long providerId) {
		InsuranceProvider admin=providerRepo.getById(id);
		InsuranceProvider provider = providerRepo.getById(providerId);
		if(admin.getCompanyName().equals(provider.getCompanyName())) {
			provider.setStatus(status);
			providerRepo.save(provider);
			return true;
		}
		return false;
	}

}
