package com.lubid.lubiduser.controller;


import com.lubid.lubiduser.Service.TestUserService;
import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author seongjung you
 * @version 0.0.1
 *
 * test를 위한 엔드포인트
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "lubid-user/auth/test")
public class TestAuthUserController {

    private final UserService userService;
    private final TestUserService testUserService;

    @PostMapping(value = "user/join")
    public ResponseEntity joinUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // 로그인 성골시 JWT발급
    @PostMapping(value = "user/login")
    public ResponseEntity loginUser(@RequestBody User user) {
        return userService.login(user);
    }

    // 로그인 시킬 테스트 계정을 받습니다.
    @PostMapping(value = "user/lubid")
    public ResponseEntity<?> loginAndCreateTestUser(@RequestBody User user) {

        testUserService.createTestUser(user);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
