package com.myFitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myFitness.encryption.EncryptPassword;
import com.myFitness.entity.Admin;
import com.myFitness.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository repository;

	public ResponseEntity<Admin> loginAdmin(Admin admin) {
		Admin admin2 = repository.getEmailByEmail(admin.getAdminEmail());
		if (admin2 != null) {
			admin.setAdminPassword(EncryptPassword.convertToMD5(admin.getAdminPassword()));
			if (admin.getAdminPassword().equals(admin2.getAdminPassword())) {
				return ResponseEntity.ok(admin2);
			}
		}
		return ResponseEntity.ok(false).status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<Boolean> saveAdmin(Admin admin) {
		admin.setAdminPassword(EncryptPassword.convertToMD5(admin.getAdminPassword()));
		if (repository.save(admin) != null) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
}
