package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RoleDTO;
import com.app.dto.UserUpdate;
import com.app.entities.User;
import com.app.services.IProviderService;
import com.app.services.UserServiceImpl;
import com.app.utils.Response;
import com.app.utils.StatusEnum;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserServiceImpl userServ;
	@Autowired
	IProviderService providerServ;
	

	
	
	@PostMapping("/updatepass")
	public String updatePassword(@RequestBody UserUpdate useData ){
		String msg="Invalid Credentials";
		Boolean emailExists=userServ.updatePasswrod(useData);
		if(emailExists==true)
		msg="Password Updated";
		return msg;
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable long userId){
		try {
			User u=userServ.getById(userId);
			return Response.success(u);	
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	
	
	//User can Update his Profile by id
	@PutMapping()
	public ResponseEntity<?> updateProfile(@RequestBody User user){
		try {
			User user1=userServ.save(user);			
			if(user1==null)				
				return Response.status(HttpStatus.NOT_FOUND);
			return Response.success(user1);				
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	//desableUserAccount by User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> desableUserAccount(@PathVariable long id){
		try {
			User u=userServ.getById(id);
			u.setStatus(StatusEnum.INACTIVE);
			userServ.save(u);
			return Response.success("Your Account has been Successfully Deactivated...ThankYou");
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	//Re-Activate User Account by User
	@GetMapping("/reactivate/{id}")
	public ResponseEntity<?> reActivateUserAccount(@PathVariable long id){
		try {
			User u=userServ.getById(id);
			u.setStatus(StatusEnum.ACTIVE);
			userServ.save(u);
			return Response.success("Your Account has been Successfully Activated...Welcome Back");
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	@PostMapping("/role")
	public ResponseEntity<?> addRole(@RequestBody RoleDTO role,HttpSession hs){
		User u=(User)hs.getAttribute("userDetails");
		if(userServ.addRole(role,u)) {
			return Response.success("Your Role has been Successfully Added...");
		}
		return Response.error("Something Went Wrong...");
	}
	
	
}

	

	

