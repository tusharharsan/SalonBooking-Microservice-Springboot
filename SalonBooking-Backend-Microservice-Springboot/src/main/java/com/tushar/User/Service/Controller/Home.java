package com.tushar.User.Service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @GetMapping
    public String HomeControllerHandler(){
        return "user Microservice for salon booking system";
    }

}
