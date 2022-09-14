package com.app.controller;





import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

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
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.UserDto;
import com.app.jwt_utils.JwtUtils;
import com.app.services.IHomeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class HomeController {

	@Autowired
	private JwtUtils utils;
	// dep : Auth mgr
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	IHomeService homeServ;
	
	@PostMapping
	public String registerUsers(@RequestBody UserDto u) {
		if(!homeServ.RegisterUser(u))
			return "Not Registered";
		return "Registered Successfully...";
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getLogin(@RequestBody @Valid AuthRequest request) {
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		log.info("auth token " + authToken);
		try {
//			 authenticate the credentials
			Authentication authenticatedDetails = manager.authenticate(authToken);
//			// => auth succcess
			return ResponseEntity.ok(new AuthResp("Auth successful!", utils.generateJwtToken(authenticatedDetails)));
		} catch (/*BadCredentials*/Exception e) { // lab work : replace this by a method in global exc handler
//			// send back err resp code
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}	
		return null;
	}
	
	@GetMapping("/policies")
	public ResponseEntity<?> getAllPolicies(){
		//return Response.success(homeServ.getAllPolicies());
		return ResponseEntity.ok(homeServ.getAllPolicies());
	}
}
