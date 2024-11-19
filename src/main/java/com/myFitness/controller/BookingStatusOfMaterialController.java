package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.BookingStatusOfMaterial;
import com.myFitness.service.BookingStatusOfMaterialService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/bookingStatus")
public class BookingStatusOfMaterialController {

	@Autowired
	private BookingStatusOfMaterialService service;
	
	@PostMapping("/bookMaterial")
	public ResponseEntity<Boolean> bookMaterial(@RequestBody BookingStatusOfMaterial book) {	
		return service.bookMaterial(book);
	}
	
	@GetMapping("/getAllBook")
	public ResponseEntity<List<BookingStatusOfMaterial>> getAllBooking() {
		return service.getAllBooking();
	}
	
	@GetMapping("/approveMaterial/{bookingMaterialId}")
	public ResponseEntity<Boolean> approveMaterial(@PathVariable("bookingMaterialId") String bookingMaterialId) {
		return service.approveMaterial(Long.parseLong(bookingMaterialId));
	}
	
	@PostMapping("/bookMaterialAsAdmin")
	public ResponseEntity<Boolean> bookAsAdmin(@RequestBody BookingStatusOfMaterial booking) {
		return service.bookAsAdmin(booking);
	}
	
	@GetMapping("/checkMemberMaterial/{memberId}")
	public List<BookingStatusOfMaterial> checkMemberMaterial(@PathVariable("memberId") String memberId) {
		return service.checkMemberMaterial(Long.parseLong(memberId));
	}
	
	@GetMapping("/rejectBooking/{bookingMaterialId}")
	public ResponseEntity<Boolean> rejectMaterial(@PathVariable("bookingMaterialId") String bookingMaterialId) {
		return service.rejectBooking(Long.parseLong(bookingMaterialId));
	}
}
