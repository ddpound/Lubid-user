package com.lubid.lubiduser.runner;

import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.config.SystemDataProperties;
import com.lubid.lubiduser.enumpack.AuthAndRoles;
import com.lubid.lubiduser.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    private final SystemDataProperties systemDataProperties;

    @Override
    @Transactional
    public void run(String... args) {
        createSuperAdmin();
    }

    /**
     * super user 의 아이디는 1
     *
     * */
    public void createSuperAdmin(){

        if (userService.findUser(1L) == null){
            User user = User.builder()
                    .userId(1L)
                    .userName(systemDataProperties.getSuperAdminName())
                    .email("admin@admin.com")
                    .remarks("시스템슈퍼계정")
                    .oauth(AuthAndRoles.Admin)
                    .password(passwordEncoder.encode(systemDataProperties.getSuperAdminPwd()))
                    .enabled(true)
                    .build();

            user.createData(1L);
            userService.saveUser(user);
        }
    }
}
