package com.myFitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myFitness.entity.Trainer;

import jakarta.transaction.Transactional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long>{

	
	@Query("SELECT t FROM Trainer t WHERE t.approve = true")
	List<Trainer> checkApproveTrainer();

	@Query("SELECT t FROM Trainer t WHERE t.email =:email")
    Trainer findByEmail(@Param("email") String email);

	@Query("UPDATE Trainer t SET t.password = :password WHERE t.email =:email")
	@Transactional
	@Modifying
	Trainer updatePassword(@Param("email") String email, @Param("password") String password);
	

	@Query("DELETE FROM Trainer t WHERE t.trainer_id =:id")
    Boolean deleteByTrainerId(@Param("id") Long id);
	
	@Query("SELECT t FROM Trainer t WHERE t.trainer_id =:id")
    Trainer checkByTrainerId(@Param("id") Long id);

	
	@Query("UPDATE Trainer t SET t.approve = true WHERE t.trainer_id =:trainer_id")
	@Modifying
	@Transactional
	int approveTrainer(@Param("trainer_id") Long trainer_id);

	
	@Query("SELECT COUNT(trainer_id) FROM Trainer WHERE approve = true")
	int numberOfTrainer();
	
}
