package com.example.demo.repository;

import com.example.demo.model.Verification;
import org.springframework.data.repository.CrudRepository;

public interface VerificationCodeRepository extends CrudRepository<Verification, Long>{
	
	Verification findByUsername(String username);
	boolean existsByUsername(String username);
}
