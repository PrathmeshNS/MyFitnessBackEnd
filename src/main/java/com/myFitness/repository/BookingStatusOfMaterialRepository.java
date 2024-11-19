package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.BookingStatusOfMaterial;

import jakarta.transaction.Transactional;

@Repository
@EnableJpaRepositories
public interface BookingStatusOfMaterialRepository extends JpaRepository<BookingStatusOfMaterial, Long> {

	
	@Transactional
	@Modifying
	@Query("UPDATE BookingStatusOfMaterial b SET b.status = true WHERE b.bookingMaterialId = :bookingMaterialId")
	int approveMaterial(@Param("bookingMaterialId") Long materialId);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM BookingStatusOfMaterial b WHERE b.bookingMaterialId = ?1", nativeQuery=true)
	int removeMaterialBooking(Long bookingMaterialId);
}
