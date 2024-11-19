package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Trainer;
import com.myFitness.service.TrainerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/trainer")
public class TrainerController {
	
	@Autowired
	TrainerService service;

	
	@GetMapping("/listOfTrainer")
	public ResponseEntity<List<Trainer>> listOfTrainer() {
		return service.listOfTrainer();
	}
	
	@PostMapping("/insertTrainer")
	public ResponseEntity<Boolean> insertTrainer(@RequestBody Trainer trainer) {
		return service.insertTrainer(trainer);	
	}
	
	@GetMapping("/checkApproveTrainer")
	public ResponseEntity<List<Trainer>> checkApproveTrainer() {
		return service.checkApproveTrainer();
	}
	
	@PostMapping("/loginTrainer")
	public ResponseEntity<Trainer> loginTrainer(@RequestBody Trainer trainer){
		return service.loginTrainer(trainer);
	}
	
	@GetMapping("/approveTrainer/{trainerId}")
	public ResponseEntity<Boolean> approveTrainer(@PathVariable("trainerId") String trainerId) {
		return service.approveTrainer(trainerId);
	}
	

	@GetMapping("searchById/{trainerId}")
	public Trainer searchById(@PathVariable("trainerId") String trainerId) {
		return service.searchById(Long.parseLong(trainerId));
	}
	
	@GetMapping("numberOfTrainer")
	public int numberOfTrainer() {
		return service.numberOfTrainer();
	}
	
	@GetMapping("searchByEmail/{trainerEmail}")
	public ResponseEntity<Trainer> checkEmail(@PathVariable("trainerEmail") String trainerEmail) {
		return service.checkEmail(trainerEmail);
	}
	
}
