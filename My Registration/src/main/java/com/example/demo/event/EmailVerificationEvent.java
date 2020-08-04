package com.example.demo.event;

import com.example.demo.model.MyAppUser;
import org.springframework.context.ApplicationEvent;

public class EmailVerificationEvent extends ApplicationEvent {

    private MyAppUser myAppUser;

    public EmailVerificationEvent(MyAppUser source) {
        super(source);
        this. myAppUser = source;
    }

    public MyAppUser getMyAppUser(){
        return myAppUser;
    }
}
