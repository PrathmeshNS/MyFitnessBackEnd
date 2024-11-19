package com.myFitness.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.entity.Attendance;
import com.myFitness.entity.Member;
import com.myFitness.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository repository;

	@Autowired(required = true)
	private Attendance attendance = new Attendance();

	@Autowired
	private MemberService memberService;

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

	/*
	 * This is method mainly do that creating the new record in the database and
	 * store the value in it. 1. By storing value manually it will be automatically
	 * added into the object. 2. By creating the object of member service and
	 * calling the memberDetailsById method to get the member all details 3. And at
	 * the end we will store the value into the database And return the appropriate
	 * message to the api request.
	 * 
	 * Author : Prathmesh N S
	 */

	public ResponseEntity<Boolean> insertAttendance(Long memberId) {
		attendance.setDate(getData());
		attendance.setInTime(getTime());
		Member member = memberService.memberDetailsById(memberId);
		attendance.setMember(member);
		if (repository.save(attendance) != null) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	/*
	 * This function check whether the record present in the database by the latest
	 * value and give me the list of attendance. Then the attendance will be
	 * iterated throughout the for loop and find the attendanceId to update the out
	 * time
	 * 
	 * Author : Prathmesh N S
	 */

	public Long checkDate(Long memberId) {
		List<Attendance> attendances = repository.getByDate(getData());
		for (Attendance attendance : attendances) {
			if (((attendance.getMember().getMemberId()) == memberId) && (attendance.getOutTime() == null)) {
				return attendance.getAttendanceId();
			}
		}
		return null;
	}

	public ResponseEntity<Boolean> insertOutTime(Long memberId) {
		Long attendanceId = checkDate(memberId);
		if (repository.insertOutTime(getTime(), attendanceId) > 0) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	public List<Attendance> customeRangeRecord(String startDate, String endDate) {
		return repository.customeRangeRecord(startDate, endDate);

	}
}
