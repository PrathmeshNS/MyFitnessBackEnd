package com.myFitness.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Trainer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trainer_id;
	private String fullName;
	private String email;
	private String mobileNo;
	private String password;
	private boolean approve;
	private String viewBooking;
	private int aqaureByMember;
	private String gender;
	private String forgetPasswordAnswer;


	@OneToMany(mappedBy = "trainer")
	@JsonIgnore
	private List<Member> members;

	

	public Long getTrainer_id() {
		return trainer_id;
	}

	public void setTrainer_id(Long trainer_id) {
		this.trainer_id = trainer_id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getViewBooking() {
		return viewBooking;
	}

	public void setViewBooking(String viewBooking) {
		this.viewBooking = viewBooking;
	}

	public boolean isApprove() {
		return approve;
	}
	
	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getAqaureByMember() {
		return aqaureByMember;
	}

	public void setAqaureByMember(int aqaureByMember) {
		this.aqaureByMember = aqaureByMember;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getForgetPasswordAnswer() {
		return forgetPasswordAnswer;
	}

	public void setForgetPasswordAnswer(String forgetPasswordAnswer) {
		this.forgetPasswordAnswer = forgetPasswordAnswer;
	}


}