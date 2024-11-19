package com.myFitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Admin;
import com.myFitness.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService service;
	
	@PostMapping("login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) {	
		return service.loginAdmin(admin);
	}
	
	@PostMapping("insertAdmin")
	public ResponseEntity<Boolean> saveAdmin(@RequestBody Admin admin) {
		return service.saveAdmin(admin);
	}
	
	
	
	
}
