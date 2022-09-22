package com.app.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.UserDto;
import com.app.entities.OTP;
import com.app.entities.Policy;
import com.app.entities.User;
import com.app.repository.IPolicyRepository;
import com.app.repository.IUserRepository;
import com.app.repository.OTPRepository;
import com.app.utils.Roles;
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
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	OTPRepository otpRepo;
	
	@Override
	public User findByEmailAndPass(String email,String pass) {
		System.out.println(email+" "+pass);
		User user = userRepo.findByEmailAndPassword(email, pass);
		if(user==null)
			return null;
		user.getRoles().size();
		return user;
	}
	
	@Override
	public boolean RegisterUser(UserDto u) {
		try {
		User u1=mapper.map(u, User.class);
		u1.setStatus(StatusEnum.ACTIVE);
		u1.setDateCreated(LocalDate.now());
		u1.setPassword(encoder.encode(u.getPassword()));
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

	@Override
	public UserDto findByEmail(String email) {
		User user=userRepo.findByEmail(email).orElseThrow();
		 UserDto userDto = mapper.map(user, UserDto.class);
		 Set<Roles> roles=new HashSet<>();
		 user.getRoles().forEach(i->roles.add(i.getRoleName()));
		 userDto.setRoles(roles);
		 return userDto;
	}

	@Override
	public List<String> getAllUserNames() {
			List<String> userNames = userRepo.getUserName();
			System.out.println(userNames.toString());
		return userNames;
	}
	@Override
	public List<String> getAllEmails() {
		List<String> emails = userRepo.getEmails();
		System.out.println(emails.toString());
	return emails;
}
	@Override
	public boolean setOtp(OTP otp) {
		otpRepo.save(otp);
		return true;
	}

	@Override
	public String validateOTP(OTP otp) {
		OTP otp2 = otpRepo.getById(otp.getEmail());
		System.out.println(otp);
		System.out.println(otp2);
		if(otp.getOtp()==otp2.getOtp()) {
			int otp3=(int) (Math.random()*9000)+1000;
			String password=otp.getEmail().substring(0,4)+"@"+otp3;
			userRepo.changePassword(otp.getEmail(),encoder.encode(password));
			return password;
		}
		return null;
	}
	
}
