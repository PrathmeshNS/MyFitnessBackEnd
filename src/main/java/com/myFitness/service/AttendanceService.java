package com.myFitness.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.entity.Attendance;
import com.myFitness.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository repository;

	private String getData() {
		String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
		return date;
	}

	private String getTime() {
		String time = new SimpleDateFormat("HH : mm : ss a").format(Calendar.getInstance().getTime());
		return time;
	}

	public ResponseEntity<List<Attendance>> getAllAttendance() {
		return ResponseEntity.ok(repository.findAll());
	}

	public ResponseEntity<String> insertAttendance(Attendance attendance) {
		attendance.setDate(getData());
		attendance.setInTime(getTime());
		if (repository.save(attendance) != null) {
			return ResponseEntity.ok("Attendance Added Sucessfully");
		}
		return ResponseEntity.ok("Something Wents Wrong");
	}

	public ResponseEntity<Boolean> insertOutTime(Long attendanceId) {
		if (repository.insertOutTime(getTime(), attendanceId)) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}
}
