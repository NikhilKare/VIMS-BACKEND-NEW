package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.dto.UserDto;
import com.app.entities.User;
import com.app.services.IHomeService;
import com.app.utils.Response;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {

	@Autowired
	IHomeService homeServ;
	
	@PostMapping
	public String registerUsers(@RequestBody UserDto u) {
		if(!homeServ.RegisterUser(u))
			return "Not Registered";
		return "Registered Successfully...";
	}
	
	@PostMapping("/login")
	public User getLogin( @RequestBody LoginDTO details,HttpSession hs) {
		System.out.println("hoyli");
		User u=homeServ.findByEmailAndPass(details.getEmail(),details.getPassword());
			System.out.println(u);			
			if(u!=null) {
				hs.setAttribute("userDetails", u);
			}
			return u;					
	}
	
	@GetMapping("/policies")
	public ResponseEntity<?> getAllPolicies(){
		//return Response.success(homeServ.getAllPolicies());
		return ResponseEntity.ok(homeServ.getAllPolicies());
	}
}
