package com.example.demo.controller;

import com.example.demo.model.MyAppUser;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HelloController {

	@GetMapping("/hello")
	public String sayHello(Model model, @RequestParam(defaultValue = "Siva" ,required = false)String name) {
		model.addAttribute("name", name);
		return "hello";
	}
	
	@GetMapping("/home")
	public String home() {
		return "hello";
	}

}
