package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

	
	@Query("SELECT m FROM Material m WHERE materialId = :materialId")
	Material getMaterialById(@Param("materialId")Long materialId);

	@Query("SELECT COUNT(materialId) FROM Material")
	int numberOfMaterial();

}
