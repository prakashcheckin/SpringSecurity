package com.example.demo.repository;

import com.example.demo.model.MyAppUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<MyAppUser, String> {

	MyAppUser findByUsername(String username);
	MyAppUser findByEmail(String email);
	
}
