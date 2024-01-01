package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.dto.UserDto;
import com.lubid.lubiduser.enumpack.AuthAndRoles;
import com.lubid.lubiduser.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Value("${default-user-password}")
    String defaultUserPassword;

    @Value("${jwt-password}")
    static String jwtPassword;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDto findUser(int userId){
        Optional<User> resultUser = userRepository.findById(userId);

        return new UserDto(resultUser.get());
    }

    public ResponseEntity createUser(User user){

        if(user.getUserName() != null){
            User findUser = userRepository.findByUserName(user);

            if(findUser != null){
                return new ResponseEntity("already user name", HttpStatus.BAD_REQUEST);
            }

            userRepository.save(User.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .password(user.getPassword() != null ? passwordEncoder.encode(user.getPassword()) : defaultUserPassword)
                    .roles(AuthAndRoles.User) // 첫 가입은 무조건 유저
                    .oauth(user.getOauth() != null ? user.getOauth() : AuthAndRoles.Lubid)
                    .build());

            return new ResponseEntity("success save user", HttpStatus.OK);
        }else{
            return new ResponseEntity("please insert user name", HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public static String generateJwtToken() {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setSubject("test")
                .signWith(SignatureAlgorithm.HS256, jwtPassword)
                .setExpiration(null); // Expired Date 구성
        return builder.compact();
    }
}
