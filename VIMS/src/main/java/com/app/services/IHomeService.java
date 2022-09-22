package com.app.services;

import java.util.List;

import com.app.dto.PolicyDetailsDTO;
import com.app.dto.UserDto;
import com.app.entities.OTP;
import com.app.entities.User;

public interface IHomeService {

	User findByEmailAndPass(String email, String pass);
	boolean RegisterUser(UserDto u);
	List<PolicyDetailsDTO> getAllPolicies();
	UserDto findByEmail(String email);
	List<String> getAllEmails();
	List<String> getAllUserNames();
	boolean setOtp(OTP otp);
	String validateOTP(OTP otp);
}
