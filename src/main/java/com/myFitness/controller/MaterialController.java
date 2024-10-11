package com.myFitness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myFitness.entity.Material;
import com.myFitness.service.MaterialService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/material")
public class MaterialController {

	@Autowired
	MaterialService service;
	
	@GetMapping("getAllMaterial")
	public ResponseEntity<List<Material>> getAllMaterial() {
		return service.getAllMaterial();
	}
	
	@PostMapping("insertMaterial")
	public ResponseEntity<Boolean> insertMaterial(@RequestBody Material material) {
		return service.insertMaterial(material);
	}
	
	@GetMapping("/getById/{materialId}")
	public ResponseEntity<Material> getMaterialById(@PathVariable("materialId") String materialId) {
		return service.getMaterialById(Long.parseLong(materialId));
	}
	
}
