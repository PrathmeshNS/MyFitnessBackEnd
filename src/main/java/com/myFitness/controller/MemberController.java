package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Member;
import com.myFitness.service.MemberService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService service;

	@PostMapping("/insertMember")
	public ResponseEntity<Boolean> insertData(@RequestBody Member member) {
		return service.saveMember(member);
	}

	@GetMapping("/getAllMemberData")
	public ResponseEntity<List<Member>> getAllMemberData() {
		return service.listOfMember();
	}

	@PostMapping("/deleteMemberById/{memberId}")
	public ResponseEntity<Boolean> deleteByMemberId(@PathVariable("memberId") Long memberId) {
		return service.deleteMemberById(memberId);
	}

	@PostMapping("/checkMemberById/{memberId}")
	public ResponseEntity<Member> searchByMemberId(@PathVariable("memberId") Long memberId) {
		return service.checkMemberById(memberId);
	}

	@PostMapping("/forgetPassword")
	public ResponseEntity<Boolean> checkForgetPassword(Member member) {
		return service.forgetPassword(member);
	}

	@PostMapping("/loginMember")
	public ResponseEntity<Member> loginMember(@RequestBody Member member) {
		return service.loginMember(member);
	}

	@PostMapping("/updatePassword")
	public ResponseEntity<Boolean> updatePassword(@RequestBody Member member) {
		return service.changePassword(member);
	}
	
	@GetMapping("/checkEmail/{email}")
	public ResponseEntity<Boolean> checkEmail(@PathVariable("email") String email) {
		return service.checkMemberEmail(email);
	}
	
	@GetMapping("numberOfMember")
	public int numberOfMember() {
		return service.numberOfMember();
	}
	
}
