package com.myFitness.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.encryption.EncryptPassword;
import com.myFitness.entity.Member;
import com.myFitness.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository repository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Member checkEmail(String email) {
//		logger.warn("I'm In Side the method");
		Member member = repository.findByEmail(email);
		if (member != null) {
			return member;
		}
		return null;
	}

	public ResponseEntity<Boolean> checkMemberEmail(String email) {
		if (checkEmail(email) == null) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<Boolean> saveMember(Member member) {
//		logger.info("hey!!");
		if (checkEmail(member.getEmail()) == null) {
			logger.atInfo();
			member.setPassword(EncryptPassword.convertToMD5(member.getPassword()));
			if (repository.save(member) != null) {
				return ResponseEntity.ok(true);
			}
		} else {
			return ResponseEntity.ok("Email Already Exits").status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.ok(false).status(HttpStatus.NOT_ACCEPTABLE).build();
	}

	public ResponseEntity<List<Member>> listOfMember() {
		return ResponseEntity.ok(repository.findAll());
	}

	public ResponseEntity<Boolean> forgetPassword(Member member) {
		Member member2 = checkEmail(member.getEmail());
		if (member2 != null) {
			if (member2.getForgetPasswordAnswer().equals(member2.getForgetPasswordAnswer().toLowerCase())) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<Boolean> changePassword(Member member) {
		if (repository.updatePassword(member.getEmail(), EncryptPassword.convertToMD5(member.getPassword())) > 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<Member> checkMemberById(Long id) {
		Member member = repository.checkByMemberId(id);
		if (member != null) {
			return ResponseEntity.ok(member);
		} else {
			return ResponseEntity.ok(null);
		}
	}

	public ResponseEntity<Boolean> deleteMemberById(Long id) {
		if (repository.deleteByMemberId(id)) {
			return ResponseEntity.ok(true);
		} else {
			return ResponseEntity.ok(false);
		}
	}

	public ResponseEntity<Member> loginMember(Member member) {
		Member member2 = checkEmail(member.getEmail());
		if (member2 != null) {
			member.setPassword(EncryptPassword.convertToMD5(member.getPassword()));
			if ((member2.getPassword().equals(member.getPassword()))) {
				return ResponseEntity.ok(member2);
			}
		}
		return ResponseEntity.ok(null);
	}

	public int numberOfMember() {
		return repository.numberOfMember();
	}

	public Member memberDetailsById(Long memberId) {
		Member member = repository.checkByMemberId(memberId);
		if (member != null) {
			return member;
		}
		return null;
	}

}
