package com.example.demo.controller;

import com.example.demo.model.MyAppUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class VerificationController {

	@Autowired
	private VerificationService verificationService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/verify/email")
	public String sayHello(@RequestParam Long id) {
		String username = verificationService.getUsernameForId(id);
		if(username != null) {
			MyAppUser user = userRepository.findByUsername(username);
			user.setEnabled(true);
			userRepository.save(user);
		}
		return "login";
	}
}
