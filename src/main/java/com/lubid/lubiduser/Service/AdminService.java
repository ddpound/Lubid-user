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

    /**
     * 모든 유저의 데이터 확인
     * */
    public ResponseEntity<User> findAllUser(){
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }

    /**
     * 유저 검색
     * */
    public ResponseEntity<User> findUserByUsername(String username){
        return new ResponseEntity<>(userRepository.findByUserName(username), HttpStatus.OK);
    }

}
