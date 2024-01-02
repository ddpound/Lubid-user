package com.lubid.lubiduser.Controller;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.dto.UserDto;
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

    @PostMapping(value = "user/join")
    public ResponseEntity joinUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // 로그인 성골시 JWT발급
    @GetMapping(value = "user/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        return userService.login(user);
    }

}
