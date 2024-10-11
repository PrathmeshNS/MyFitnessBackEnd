package com.myFitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.entity.BookingStatusOfMaterial;
import com.myFitness.repository.BookingStatusOfMaterialRepository;

@Service
public class BookingStatusOfMaterialService {

	@Autowired
	BookingStatusOfMaterialRepository repository;
	
	public ResponseEntity<Boolean> bookMaterial(BookingStatusOfMaterial book) {
		if ((book.getMember().getMemberId()!=null) && (book.getMaterial().getMaterialId()!=null)) {
			book.setStatus(false);
			if (repository.save(book)!=null) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<List<BookingStatusOfMaterial>> getAllBooking() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	public ResponseEntity<Boolean> approveMaterial(Long materialId) {
		if (repository.approveMaterial(materialId)) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	public ResponseEntity<Boolean> bookAsAdmin(BookingStatusOfMaterial bookMaterial) {
		if ((bookMaterial.getMember().getMemberId()!=null) && (bookMaterial.getMaterial().getMaterialId()!=null)) {
			bookMaterial.setStatus(true);
			if (repository.save(bookMaterial)!=null) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}
}
