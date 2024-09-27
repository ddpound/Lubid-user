package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.JwtMappingRepository;
import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.dto.UserDto;
import com.lubid.lubiduser.enumpack.AuthAndRoles;
import com.lubid.lubiduser.model.JwtMappingUser;
import com.lubid.lubiduser.model.User;
import com.lubid.lubiduser.module.MakeJWT;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final JwtMappingRepository jwtMappingRepository;

    private final PasswordEncoder passwordEncoder;

    private final MakeJWT makeJWT;

    @Transactional(readOnly = true)
    public UserDto findUser(Long userId){
        Optional<User> resultUser = userRepository.findById(userId);

        return resultUser.map(UserDto::new).orElse(null);
    }

    public UserDto findUserName(String username){
        User findUser = userRepository.findByUserName(username);
        return new UserDto(findUser);
    }

    @Transactional
    public Integer saveUser(User user){
        userRepository.save(user);
        return 1;
    }

    @Transactional
    public ResponseEntity<?> createUser(User user){

        if(user.getUserName() != null){
            User findUser = userRepository.findByUserName(user.getUserName());

            if(findUser != null){
                return new ResponseEntity<>("already user name", HttpStatus.BAD_REQUEST);
            }

            User insertUser = User.builder()
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(defaultUserPassword))
                    .roles("ROLE_USER") // 첫 가입은 무조건 유저
                    .oauth(user.getOauth() != null ? user.getOauth() : AuthAndRoles.Lubid)
                    .build();

            // 데이터 생성
            insertUser.createData(1L);

            userRepository.save(insertUser);

            return new ResponseEntity<>("success save user", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("please insert user name", HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    // 로그인 성공시 줘야하는 것 바디나 헤더에 JWT 담아주기
    @Transactional
    public ResponseEntity<?> login(User user){
        try {
            if(user.getUserName() != null && user.getPassword() != null){
                User findUser = userRepository.findByUserName(user.getUserName());
                if(findUser == null) throw new NullPointerException();

                if(passwordEncoder.matches(user.getPassword(),findUser.getPassword())){
                    String createToken = makeJWT.createToken(findUser);

                    // 종속화
                    JwtMappingUser findJwtMappingUser = jwtMappingRepository.findByUser(findUser);

                    // 토큰 매핑 저장
                    if(findJwtMappingUser != null){
                        // 더티체킹
                        findJwtMappingUser.setToken(createToken);
                    }else{
                        jwtMappingRepository.save(JwtMappingUser.builder().user(findUser).token(createToken).build());
                    }
                    return new ResponseEntity<>(createToken,HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("please input username",HttpStatus.BAD_REQUEST);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>("not found username",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("sorry server error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("sorry bad request",HttpStatus.BAD_REQUEST);
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
