package com.Tushar.Salon_Service.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String HomeControllerHandler(){
        return "salon Microservice for salon booking system";
    }

}
