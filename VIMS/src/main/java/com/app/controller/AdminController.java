package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.IUserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IUserService userServ;
	
	
	
}
