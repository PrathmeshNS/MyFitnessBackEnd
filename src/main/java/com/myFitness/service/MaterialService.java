package com.myFitness.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.entity.Material;
import com.myFitness.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
 	private MaterialRepository repository;
	
	public ResponseEntity<List<Material>> getAllMaterial() {
		return ResponseEntity.ok(repository.findAll());
	}

	public ResponseEntity<Boolean> insertMaterial(Material material) {
		if (repository.save(material)!=null) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
	
	public ResponseEntity<Material> getMaterialById(Long materialId) {
		return ResponseEntity.ok(repository.getMaterialById(materialId));
	}

	public int numberOfMaterial() {
		return repository.numberOfMaterial();
	}

}
