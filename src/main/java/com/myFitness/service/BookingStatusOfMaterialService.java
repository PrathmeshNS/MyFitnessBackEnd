package com.myFitness.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.entity.BookingStatusOfMaterial;
import com.myFitness.repository.BookingStatusOfMaterialRepository;

@Service
public class BookingStatusOfMaterialService {

	@Autowired
	private BookingStatusOfMaterialRepository repository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ResponseEntity<Boolean> bookMaterial(BookingStatusOfMaterial book) {
		if ((book.getMember().getMemberId() != null) && (book.getMaterial().getMaterialId() != null)) {
			book.setStatus(false);
			if (repository.save(book) != null) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<List<BookingStatusOfMaterial>> getAllBooking() {
		return ResponseEntity.ok(repository.findAll());
	}

	public ResponseEntity<Boolean> approveMaterial(Long materialId) {
		if (repository.approveMaterial(materialId) > 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<Boolean> bookAsAdmin(BookingStatusOfMaterial bookMaterial) {
		if ((bookMaterial.getMember().getMemberId() != null) && (bookMaterial.getMaterial().getMaterialId() != null)) {
			bookMaterial.setStatus(true);
			if (repository.save(bookMaterial) != null) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}

	private List<BookingStatusOfMaterial> returnBookMaterials = new ArrayList<>();

	public List<BookingStatusOfMaterial> checkMemberMaterial(long memberId) {
		// Alternatively, you can initialize it at the start of the method if needed
		returnBookMaterials = new ArrayList<>(); // Ensures a fresh list each time this method is called

		List<BookingStatusOfMaterial> bookedMaterials = repository.findAll();

		for (BookingStatusOfMaterial bookMaterial : bookedMaterials) {
			if (bookMaterial.getMember().getMemberId() == memberId) {
				returnBookMaterials.add(bookMaterial);
			}
		}

		if (!returnBookMaterials.isEmpty()) {
			System.out.println(returnBookMaterials);
			return returnBookMaterials;
		} else {
			logger.info("I'm Null");
			return null;
		}
	}

	public ResponseEntity<Boolean> rejectBooking(Long bookingId) {
		if ( repository.removeMaterialBooking(bookingId)>0){
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

}
