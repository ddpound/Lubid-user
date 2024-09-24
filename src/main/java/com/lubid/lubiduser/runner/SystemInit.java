package com.lubid.lubiduser.runner;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 앱 초기 실행시 생성되는 데이터
 *
 * */
@Log4j2
@RequiredArgsConstructor
@Component
public class SystemInit implements CommandLineRunner {

    private final UserService userService;


    @Override
    public void run(String... args) throws Exception {

    }


    /**
     * super user 의 아이디는 1
     *
     * */
    public void createSuperAdmin(){

        userService.createUser(User.builder().userId(1).email("admin@admin.com").build());
    }
}
