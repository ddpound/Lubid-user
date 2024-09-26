package com.lubid.lubiduser.runner;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.enumpack.AuthAndRoles;
import com.lubid.lubiduser.model.User;
import jakarta.transaction.Transactional;
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
        createSuperAdmin();
    }


    /**
     * super user 의 아이디는 1
     *
     * */
    @Transactional
    public void createSuperAdmin(){

        if (userService.findUser(1) == null){
            User user = User.builder()
                    .userId(1)
                    .userName("userAdmin")
                    .email("admin@admin.com")
                    .oauth(AuthAndRoles.Admin)
                    .build();

            user.createData(1L);

            userService.createUser(user);
        }
    }
}
