package com.myFitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Member;

import jakarta.transaction.Transactional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	
	
	@Query("SELECT m FROM Member m WHERE m.email = :email")
    Member findByEmail(@Param("email") String email);

	
	
	@Query("UPDATE Member m SET m.password = :password WHERE m.email = :email")
	@Transactional
	@Modifying
	Member updatePassword(@Param("email") String email, @Param("password") String password);
	

	@Query("DELETE FROM Member m WHERE m.memberId = :id")
    Boolean deleteByMemberId(@Param("id") Long id);
	
	@Query("SELECT m FROM Member m WHERE m.memberId = :id")
    Member checkByMemberId(@Param("id") Long id);
	
	
}
