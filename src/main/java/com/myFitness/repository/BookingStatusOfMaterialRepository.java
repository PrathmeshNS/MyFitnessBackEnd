package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.BookingStatusOfMaterial;

import jakarta.transaction.Transactional;

@Repository
public interface BookingStatusOfMaterialRepository extends JpaRepository<BookingStatusOfMaterial, Long> {

	
	@Query("UPDATE BookingStatusOfMaterial b SET b.status = true WHERE b.bookingMaterialId = :bookingMaterialId")
	@Transactional
	@Modifying
	boolean approveMaterial(@Param("bookingMaterialId") Long materialId);

}
