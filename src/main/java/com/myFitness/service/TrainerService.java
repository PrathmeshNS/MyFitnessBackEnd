package com.myFitness.service;

import java.util.List;

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
	TrainerRepository repository;

	private Trainer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	public ResponseEntity<List<Trainer>> listOfTrainer() {
		return ResponseEntity.ok(repository.findAll());
	}

	public ResponseEntity<Boolean> insertTrainer(Trainer trainer) {
		if (findByEmail(trainer.getEmail())==null) {
			trainer.setPassword(EncryptPassword.convertToMD5(trainer.getPassword()));
			trainer.setApprove(false);
			if (repository.save(trainer)!=null) {
				return ResponseEntity.ok(true).status(HttpStatus.CREATED).build();
			}
		}else{
			return ResponseEntity.ok("Trainer Already Exits!!").status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		return ResponseEntity.ok(false).status(HttpStatus.FORBIDDEN).build();
	}

	public ResponseEntity<List<Trainer>> checkApproveTrainer() {
		return ResponseEntity.ok(repository.checkApproveTrainer());
	}

	public ResponseEntity<Trainer> loginTrainer(Trainer trainer) {
		Trainer trainer2 = findByEmail(trainer.getEmail());
		if (trainer2!=null) {
			trainer.setPassword(EncryptPassword.convertToMD5(trainer.getPassword()));
			if ((trainer.getEmail().equals(trainer2.getEmail())) && (trainer.getPassword().equals(trainer2.getPassword()))) {
				if (trainer2.isApprove()) {
					return ResponseEntity.ok(trainer2).status(HttpStatus.OK).build();
				}
				return ResponseEntity.ok("Trainer is Not Approved").status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		}
		return ResponseEntity.ok(false).status(HttpStatus.NO_CONTENT).build();
	}

	public ResponseEntity<Boolean> approveTrainer(String trainerId) {
		return ResponseEntity.ok(repository.approveTrainer(Long.parseLong(trainerId)));
	}

	public Trainer searchById(Long trainerId) {
		return repository.checkByTrainerId(trainerId);
	}
}
