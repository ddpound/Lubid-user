package com.lubid.lubiduser.Controller;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "auth")
public class AuthUserController {

    private final UserService userService;

    @PostMapping(value = "join-user")
    public ResponseEntity joinUser(@RequestBody User user) {
        
        return new ResponseEntity("success join", HttpStatus.OK);
    }

}
