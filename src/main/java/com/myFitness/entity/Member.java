package com.myFitness.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Member {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memberId;

	private String firstName;
	private String lastName;
	private String password;
	private String gender;
	private String email;
	private String mobileNo;
	private String forgetPasswordAnswer;

	/*
	 * Here is an relationship between the member and trainer 
	 * this table help a admin to manage to track that which trainer  train which member.
	 */
	@ManyToOne
	@JoinColumn(name = "trainer_id")
//	@JsonIgnore
	private Trainer trainer;
	
	/*
	 * Here is an relationship between the member and attendace?
	 * this table help a admin to manage record were which member come to gym on which date..
	 */
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Attendance> attendances = new HashSet<Attendance>();
	
	
	/*
	 * Here is an relationship between the member and Material booking status if admin id apporve it?
	 * this table help a admin to manage booking and track the records.
	 */
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<BookingStatusOfMaterial> statusofMaterials  = new HashSet<>();
	
	

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Set<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(Set<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Set<BookingStatusOfMaterial> getStatusofMaterials() {
		return statusofMaterials;
	}

	public void setStatusofMaterials(Set<BookingStatusOfMaterial> statusofMaterials) {
		this.statusofMaterials = statusofMaterials;
	}

	public String getForgetPasswordAnswer() {
		return forgetPasswordAnswer;
	}

	public void setForgetPasswordAnswer(String forgetPasswordAnswer) {
		this.forgetPasswordAnswer = forgetPasswordAnswer;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
