package com.example.demo.Listener;

import com.example.demo.event.EmailVerificationEvent;
import com.example.demo.model.MyAppUser;
import com.example.demo.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailVerificationListener {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VerificationService verificationService;

    @EventListener
    public void listen(EmailVerificationEvent event) {

        String username = event.getMyAppUser().getUsername();
        Long verificationId = verificationService.createVerification(username);
        String email = event.getMyAppUser().getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Account Verification Email");
        message.setText("Account activation link: http://localhost:8080/verify/email?id="+verificationId);
        message.setTo(email);
        mailSender.send(message);
        System.err.println("**************Mail Sentttttttttttttttt");
    }
}
