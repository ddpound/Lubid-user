package com.lubid.lubiduser.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth/test")
public class TestController {

    @GetMapping(value = "welcome")
    public String getWelcom(){
        return "welcome";
    }
}
