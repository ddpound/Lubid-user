package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUser(int userId){
        Optional<User> resultUser = userRepository.findById(userId);
        return resultUser.get();
    }

    public int createUser(User user){
        userRepository.save(user);
        return 0;
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public static String generateJwtToken() {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setSubject("test")
                .signWith(SignatureAlgorithm.HS256, "test")
                .setExpiration(null);                    // Expired Date 구성
        return builder.compact();
    }
}
