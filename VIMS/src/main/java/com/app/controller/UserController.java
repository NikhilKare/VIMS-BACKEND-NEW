package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.RoleDTO;
import com.app.dto.UserDto;
import com.app.dto.UserUpdate;
import com.app.entities.User;
import com.app.services.IProviderService;
import com.app.services.IUserService;
import com.app.utils.Response;
import com.app.utils.Roles;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	
	@Autowired
	IUserService userServ;
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
			UserDto u=userServ.getById(userId);
			return Response.success(u);	
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	
	
	//User can Update his Profile by id
	@PutMapping()
	public ResponseEntity<?> updateProfile(@RequestBody UserDto user){
//		try {
			System.out.println("in update"+user);
			User user1=userServ.save(user);	
			System.out.println(user1);
			if(user1==null)				
				return Response.status(HttpStatus.NOT_FOUND);
			return Response.success(user1);				
//		}catch (Exception e) {
//			return Response.error(e.getMessage());
//		}
	}
	
	//desableUserAccount by User
	@DeleteMapping("/{id}")
	public ResponseEntity<?> desableUserAccount(@PathVariable long id){
		try {
			
			userServ.changeStatusAccount(id);
			return Response.success("Your Account has been Successfully Deactivated...ThankYou");
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	
	//Re-Activate User Account by User
	@GetMapping("/reactivate/{id}")
	public ResponseEntity<?> reActivateUserAccount(@PathVariable long id){
		try {
			userServ.changeStatusAccount(id);
			return Response.success("Your Account has been Successfully Activated...Welcome Back");
		}catch (Exception e) {
			return Response.error(e.getMessage());
		}
	}
	@PostMapping("/{id}/role")
	public ResponseEntity<?> addRole(@RequestBody RoleDTO role,@PathVariable long id){
		
		if(userServ.addRole(role,id)) {
			return Response.success("Your Role has been Successfully Added...");
		}
		return Response.error("Something Went Wrong...");
	}
	
	@PostMapping("/{userId}/image")
	public ResponseEntity<?> uploadImage(@PathVariable long userId, @RequestParam MultipartFile imageFile) throws IOException {
		System.out.println("in upload image " + userId);
		System.out.println("uploaded img file name " + imageFile.getOriginalFilename() + " content type "
				+ imageFile.getContentType() + " size " + imageFile.getSize());
		UserDto storeImage = userServ.uploadImage(userId, imageFile);
		return ResponseEntity.ok(storeImage);
	}
	@GetMapping(value = "/{userId}/image", produces = { MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable int userId) throws IOException {
		System.out.println("in img download " + userId);
		byte[] imageContext=userServ.restoreImage(userId);
		return ResponseEntity.ok(imageContext);
	}
	
	@DeleteMapping("/{id}/role/{roleName}")
	public ResponseEntity<?> removeRole(@PathVariable long id,@PathVariable Roles roleName){
		if(userServ.removeRole(roleName, id)) {
			return ResponseEntity.ok("Role Removed Successfully");
		};
		return ResponseEntity.badRequest().body("Cannot remove role");
		
	}
}

	

	

