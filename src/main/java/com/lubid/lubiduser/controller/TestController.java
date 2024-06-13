package com.lubid.lubiduser.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lubid-user/auth/test")
public class TestController {
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "welcome")
    public String getWelcom(){
        return "welcome";
    }
}
