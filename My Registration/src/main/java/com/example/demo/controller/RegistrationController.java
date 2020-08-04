package com.example.demo.controller;

import com.example.demo.event.EmailVerificationEvent;
import com.example.demo.model.MyAppUser;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRegistrationService service;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult result) {
        if(result.hasErrors()) {
            return "register";
        }

        //TODO-16 In the below MyAppUser Constructor call, make the last parameter as false to disable the user

        MyAppUser myuser = new MyAppUser(user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                //user.getPassword(),true);
                encoder.encode(user.getPassword()),false);
        service.createUser(myuser);

        System.out.println("Email Event triggered ::: >>>");
        //TODO-17 uncomment the below line to publish UserRegistrationEvent
        eventPublisher.publishEvent(new EmailVerificationEvent(myuser));

        return "redirect:register?success";
    }
}
