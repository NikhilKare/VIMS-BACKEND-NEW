package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDTO;
import com.app.dto.UserDto;
import com.app.entities.User;
import com.app.services.IProviderService;
import com.app.services.UserServiceImpl;

@RestController
@RequestMapping
public class HomeController {

	@Autowired
	UserServiceImpl userServ;
	@Autowired
	IProviderService providerServ;
	
	@PostMapping
	public String registerUsers(@RequestBody UserDto u) {
		if(!userServ.RegisterUser(u))
			return "Not Registered";
		return "Registered Successfully...";
	}
	
	@PostMapping("/login")
	public User getLogin( @RequestBody LoginDTO details,HttpSession hs) {
		System.out.println("hoyli");
		User u=userServ.findByEmailAndPass(details.getEmail(),details.getPassword());
			System.out.println(u);			
			if(u!=null) {
				hs.setAttribute("userDetails", u);
			}
			return u;					
	}
}
