package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transaction;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.RoleDTO;
import com.app.dto.UserDto;
import com.app.dto.UserUpdate;
import com.app.entities.Customer;
import com.app.entities.InsuranceProvider;
import com.app.entities.Policy;
import com.app.entities.User;
import com.app.repository.ICustomerRepository;
import com.app.repository.IPolicyRepository;
import com.app.repository.IProviderRepository;
import com.app.repository.IRolesRepository;
import com.app.repository.IUserRepository;
import com.app.utils.Roles;
import com.app.utils.StatusEnum;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IRolesRepository roleRepo;
	@Autowired
	IProviderRepository providerRepo;
	@Autowired
	ICustomerRepository custRepo;
	@Autowired
	IPolicyRepository policyRepo;

	@Autowired
	ModelMapper mapper;
	
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
	public boolean updatePasswrod(UserUpdate user1) {
		User user=userRepo.findByEmailAndPassword(user1.getEmail(),user1.getOldPass());
		if(user==null)
			return false;
		user.setPassword(user1.getNewPass());
		userRepo.save(user);
		return true;
	}
	@Override
	public User save(User user) {
		return userRepo.save(user);
	}
	@Override
	public User getById(long userId) {
		User u=userRepo.getById(userId);
		u.getRoles();
		return u;
		
	}
	@Override
	public boolean addRole(RoleDTO role, User u) {
		u.getRoles().add(roleRepo.getByRoleName(role.getRoles()));
		u = userRepo.save(u);
		if(role.getRoles()==Roles.POLICY_PROVIDER) {
			InsuranceProvider provider=new InsuranceProvider();			
			provider.setCompanyName(role.getCompanyName());
			provider.setUserId(u);
			providerRepo.save(provider);
			return true;
		}else if(role.getRoles()==Roles.CUSTOMER){
			Customer cust=new Customer();		    
			cust.setUserId(u);
			cust.setLicenceNo(role.getLicenceNo());
			custRepo.save(cust);
			
			return true;
		}
		return false;
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
