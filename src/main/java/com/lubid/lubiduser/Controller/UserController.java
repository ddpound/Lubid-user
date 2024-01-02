package com.lubid.lubiduser.Controller;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.dto.UserDto;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "find-user/{id}")
    public UserDto findUserId(@PathVariable String id){
        return userService.findUser(Integer.parseInt(id));
    }

    @GetMapping(value = "find/{username}")
    public UserDto findByUserName(@PathVariable String username){
        return userService.findUserName(username);
    }

    @PostMapping(value = "save-user")
    public ResponseEntity saveUser(@RequestBody User user){
        return userService.createUser(user);
    }

    /**
     * 로그인 성공시 JWT 발급
     * */
    @PostMapping(value = "login-user")
    public String loginUser(@RequestBody User user){

        return "jwt";
    }

    @GetMapping(value = "find-user/sample")
    public List<User> findUserSample(){

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(restTemplate.getForEntity("http://localhost:7004/find-all-sample",Object.class));
        return userService.findAllUser();
    }

}
