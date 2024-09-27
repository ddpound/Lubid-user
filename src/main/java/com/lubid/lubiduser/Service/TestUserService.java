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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author seongjung you
 * @since 0.0.1
 *
 * 테스트 계정을 위한 테스트 서비스 단
 * */
@PropertySource(value = {"classpath:testData.yml","classpath:jwt.yml"})
@RequiredArgsConstructor
@Service
public class TestUserService {

    private static final Logger log = LoggerFactory.getLogger(TestUserService.class);
    // test key check
    @Value("${test-key}")
    String testKey;

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

        return new UserDto(resultUser.get());
    }

    public UserDto findUserName(String username){
        User findUser = userRepository.findByUserName(username);
        return new UserDto(findUser);
    }

    @Transactional
    public ResponseEntity<?> createTestUser(User user) {
        log.info(testKey);
        log.info(user.getPassword());
        try {
            // check create test user, 테스트 권한 비밀번호 확인
            // user 객체의 password가 testkey 인증하는 키가 된다.
            if (user.getPassword().equals(testKey)) {
                if (user.getUserName() != null) {
                    User findUser = userRepository.findByUserName(user.getUserName());

                    User insertUser = User.builder()
                            .userName(user.getUserName())
                            .email("test@lubid.com")
                            .password(passwordEncoder.encode(testKey))
                            .roles("ROLE_USER") // 첫 가입은 무조건 유저
                            .oauth(user.getOauth() != null ? user.getOauth() : AuthAndRoles.Lubid)
                            .build();

                    if (findUser != null) {
                        return testLogin(User.builder()
                                .userName(user.getUserName())
                                .email("test@lubid.com")
                                .password(testKey)
                                .roles("ROLE_USER") // 첫 가입은 무조건 유저
                                .oauth(user.getOauth() != null ? user.getOauth() : AuthAndRoles.Lubid)
                                .build());
                    }else{
                        userRepository.save(insertUser);

                        return testLogin(User.builder()
                                .userName(user.getUserName())
                                .email("test@lubid.com")
                                .password(testKey)
                                .roles("ROLE_USER") // 첫 가입은 무조건 유저
                                .oauth(user.getOauth() != null ? user.getOauth() : AuthAndRoles.Lubid)
                                .build());
                    }
                } else {
                    return new ResponseEntity<>("please insert test user name", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("sorry not same test key password", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    // 로그인 성공시 줘야하는 것 바디나 헤더에 JWT 담아주기
    @Transactional
    public ResponseEntity<?> testLogin(User user){
        try {
            if(user.getUserName() != null && user.getPassword() != null){
                User findUser = userRepository.findByUserName(user.getUserName());
                if(findUser == null) throw new NullPointerException();

                if(passwordEncoder.matches(user.getPassword(), findUser.getPassword())){
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
                }else{
                    return new ResponseEntity<>("sorry not same test key password", HttpStatus.BAD_REQUEST);
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
