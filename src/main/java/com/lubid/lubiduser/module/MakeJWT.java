package com.lubid.lubiduser.module;

import com.lubid.lubiduser.Repository.JwtMappingRepository;
import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.JwtMappingUser;
import com.lubid.lubiduser.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@PropertySource("classpath:jwt.yml")
@RequiredArgsConstructor
@Service
public class MakeJWT {

    private final UserRepository userRepository;

    private final JwtMappingRepository jwtMappingRepository;

    @Value("${jwt-password}")
    String secretKey;

    @Value("${expiration-hours}")
    long expirationHours;

    @Value("${issuer}")
    String issuer;

    public String createToken(User user) {
        return Jwts.builder()
                .signWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))   // HS512 알고리즘을 사용하여 secretKey를 이용해 서명
                .setSubject(user.getUserName())  // JWT 토큰 제목
                .claim("user_role", user.getRoles())
                .setIssuer(issuer)  // JWT 토큰 발급자
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))    // JWT 토큰 발급 시간
                .setExpiration(Date.from(Instant.now().plus(expirationHours, ChronoUnit.HOURS)))    // JWT 토큰 만료 시간
                .compact(); // JWT 토큰 생성
    }

    @Transactional
    public Claims validateTokenAndGetSubject(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            e.printStackTrace();
            User findUser =  userRepository.findByUserName(Jwts.claims().getSubject());
            JwtMappingUser findJwtMappingUser = jwtMappingRepository.findByUser(findUser);
            jwtMappingRepository.delete(findJwtMappingUser);
            return null;
        }

    }

}
