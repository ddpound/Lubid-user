package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 어드민 권한을 주는 메소드
     * */
    public ResponseEntity giveAdminAuthority(User user){


        return new ResponseEntity("", HttpStatus.OK);
    }

}
