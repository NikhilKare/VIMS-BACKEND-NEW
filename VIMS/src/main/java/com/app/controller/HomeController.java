package com.app.controller;





import java.time.LocalDateTime;
import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.UserDto;
import com.app.entities.OTP;
import com.app.entities.User;
import com.app.jwt_utils.JwtUtils;
import com.app.mailservice.EmailService;
import com.app.services.IHomeService;
import com.app.utils.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin//(origins = "http://localhost:3000")
@Slf4j
public class HomeController {

	@Autowired
	private JwtUtils utils;
	// dep : Auth mgr
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	IHomeService homeServ;
	
	@Autowired
	EmailService mail;
	
	

	@PostMapping
	public String registerUsers(@RequestBody UserDto u) {
		if(!homeServ.RegisterUser(u))
			return "Not Registered";
		mail.sendSimpleMessage(u.getEmail(),	"Registration : VEHICLE INSURANCE BAZAR.","<div> <span>Dear <strong><a href=mailto:"+u.getEmail()+" target=_blank>"+u.getEmail()+"</a></strong>,</span><br>"
				+ "<p></p><p><b>Congratulations. You have <span class=il>successfully</span> registered on Vehicle Insurance Bazar .</b></p><p>Your login credentials as follows <br> "
				+ "<b>UserName : </b> <a href=mailto:"+u.getEmail()+" target=_blank>"+u.getEmail()+"</a><br>"
				+ "<b>Password : </b>"+u.getPassword()+"</p> <p></p> --<br>"
				+ "<span>Regards,</span><br>"
				+ "<span>VEHICLE INSURANCE BAZAR</span><br>"
				+ "<span>Ganeshkhind Road,</span><br><span>PUNE 411 xxx</span>"
				+ "<div class=yj6qo></div><div class=adL></div></div>");
		return "Registered Successfully...";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getLogin(@RequestBody @Valid AuthRequest request) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		log.info("auth token " + authToken);
		try {
			Authentication authenticatedDetails = manager.authenticate(authToken);
			return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails),homeServ.findByEmail(request.getEmail())));
		} catch (BadCredentialsException e) { // lab work : replace this by a method in global exc handler
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}	
		
	}
	
	@GetMapping("/policies")
	public ResponseEntity<?> getAllPolicies(){
		//return Response.success(homeServ.getAllPolicies());
		return ResponseEntity.ok(homeServ.getAllPolicies());
	}
	
	@GetMapping("/emails")
	public ResponseEntity<?> getAllEmails(){
		//return Response.success(homeServ.getAllPolicies());
		return ResponseEntity.ok(homeServ.getAllEmails());
	}
	@GetMapping("/unames")
	public ResponseEntity<?> getAllUserNames(){
		return ResponseEntity.ok(homeServ.getAllUserNames());
	}
	
	@GetMapping("/forgetpass")
	public ResponseEntity<?> getOTP(@RequestParam String email){
		UserDto user = homeServ.findByEmail(email);
		if(user!=null) {
			int otp=(int) (Math.random()*9000)+1000;
			OTP otp1=new OTP();
			otp1.setEmail(email);
			otp1.setOtp(otp);
			otp1.setDateCreated(LocalDateTime.now());
			
			if(homeServ.setOtp(otp1)) {
				System.out.println("sending mail..............");
			String msg="<div  class=a3s aiL ><div><p>Dear Customer, <br> </p> <p>Your <span class=il>OTP</span> is <b>"+otp+"</b> .OTP is valid for 10 minutes only.<br/> Do not share it with anyone by any means. This is confidential and to be used by you only.</p> <p>Warm regards, <br>Vehicle Insurance Bazaar</p></div><div class=yj6qo></div><div class=adL>\r\n"
				+ "</div></div>";
				mail.sendSimpleMessage(email, "OTP Request",msg);
				System.out.println("Returning");
				return Response.success("OTP Sent Successfully...");
			}	
		}
		return Response.error("Something Went Wrong !!!");
	}
	
	@PostMapping("/forgetpass")
	public ResponseEntity<?> sendOTP(@RequestBody OTP otp){
		String jwtToken=homeServ.validateOTP(otp);
		if(jwtToken==null)
			return Response.error("Invalid OTP !!!");
		System.out.println(jwtToken);
		return ResponseEntity.ok(new AuthResp("Auth successful!",jwtToken,homeServ.findByEmail(otp.getEmail())));
	}
}