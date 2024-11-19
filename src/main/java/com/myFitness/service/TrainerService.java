package com.myFitness.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.encryption.EncryptPassword;
import com.myFitness.entity.Trainer;
import com.myFitness.repository.TrainerRepository;

@Service
public class TrainerService {

	@Autowired
	private TrainerRepository repository;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private Trainer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public ResponseEntity<List<Trainer>> listOfTrainer() {
		return ResponseEntity.ok(repository.findAll());
	}

	@SuppressWarnings("static-access")
	public ResponseEntity<Boolean> insertTrainer(Trainer trainer) {
		if (findByEmail(trainer.getEmail()) == null) {
			trainer.setPassword(EncryptPassword.convertToMD5(trainer.getPassword()));
			trainer.setApprove(false);
			if (repository.save(trainer) != null) {
				return ResponseEntity.ok(true);
			}
		} else {
			return ResponseEntity.ok("Trainer Already Exits!!").status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.ok(false);
	}

	public ResponseEntity<List<Trainer>> checkApproveTrainer() {
		return ResponseEntity.ok(repository.checkApproveTrainer());
	}

	public ResponseEntity<Trainer> loginTrainer(Trainer trainer) {
		Trainer trainer2 = findByEmail(trainer.getEmail());
		if (trainer2 != null) {
			trainer.setPassword(EncryptPassword.convertToMD5(trainer.getPassword()));
			if (trainer.getPassword().equals(trainer2.getPassword())) {
				return ResponseEntity.ok(trainer2);
			}
		}
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<Boolean> approveTrainer(String trainerId) {
		if (repository.approveTrainer(Long.parseLong(trainerId)) > 0) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}

	public Trainer searchById(Long trainerId) {
		return repository.checkByTrainerId(trainerId);
	}

	public ResponseEntity<Trainer> checkEmail(String email) {
		Trainer trainer = findByEmail(email);
		if (trainer != null) {
			return ResponseEntity.ok(trainer);
		}
		return ResponseEntity.ok(trainer);
	}

	public int numberOfTrainer() {
		return repository.numberOfTrainer();
	}
}
