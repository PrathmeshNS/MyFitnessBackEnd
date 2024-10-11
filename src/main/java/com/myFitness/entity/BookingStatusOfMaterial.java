package com.myFitness.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookingStatusOfMaterial {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookingMaterialId;
	private boolean status;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "materialId")
	private Material material;
	

	public Long getBookingMaterialId() {
		return bookingMaterialId;
	}


	public void setBookingMaterialId(Long bookingMaterialId) {
		this.bookingMaterialId = bookingMaterialId;
	}

	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}

	
	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public Material getMaterial() {
		return material;
	}


	public void setMaterial(Material material) {
		this.material = material;
	}
	
	

}