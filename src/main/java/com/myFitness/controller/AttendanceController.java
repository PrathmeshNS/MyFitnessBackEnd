package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Attendance;
import com.myFitness.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin("http://localhost:4200")
public class AttendanceController {

	@Autowired
	AttendanceService service;
	
	@GetMapping("getAllAttendance")
	public ResponseEntity<List<Attendance>> getAllAttendance() {
		return service.getAllAttendance();
	}
	
	
}
