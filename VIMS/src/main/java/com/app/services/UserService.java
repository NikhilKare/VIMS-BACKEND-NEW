package com.app.services;

import java.time.LocalDate;

import javax.transaction.Transaction;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.Customer;
import com.app.entities.InsuranceProvider;
import com.app.entities.Roles;
import com.app.entities.StatusEnum;
import com.app.entities.User;
import com.app.repository.ICustomerRepository;
import com.app.repository.IProviderRepository;
import com.app.repository.IRolesRepository;
import com.app.repository.IUserRepository;

import DTO.RoleDTO;
import DTO.UserDto;
import DTO.UserUpdate;

@Service
@Transactional
public class UserService implements IUserService{
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IRolesRepository roleRepo;
	@Autowired
	IProviderRepository providerRepo;
	@Autowired
	ICustomerRepository custRepo;
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
}
