package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	
	@Query("SELECT a FROM Admin a WHERE a.adminEmail = :adminEmail")
	Admin getEmailByEmail(@Param("adminEmail")String adminEmail);
	
	

}
