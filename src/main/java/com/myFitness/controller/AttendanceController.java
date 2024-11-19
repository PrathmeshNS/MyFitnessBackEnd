package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Attendance;
import com.myFitness.service.AttendanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/attendance/")
public class AttendanceController {

	@Autowired
	AttendanceService service;

	@GetMapping("getAllAttendance")
	public ResponseEntity<List<Attendance>> getAllAttendance() {
		return service.getAllAttendance();
	}

	@GetMapping("insertInTime/{memberId}")
	public ResponseEntity<Boolean> insertInTime(@PathVariable("memberId") String memberId) {
		return service.insertAttendance(Long.parseLong(memberId));
	}

	@GetMapping("insertOutTime/{memberId}")
	public ResponseEntity<Boolean> insertOutTime(@PathVariable("memberId") String memberId) {
		return service.insertOutTime(Long.parseLong(memberId));
	}

	@PostMapping("customeDate")
	public List<Attendance> customeRangeRecord(@RequestParam String startDate, String endDate) {
		return service.customeRangeRecord(startDate, endDate);
	}

}
