package com.lubid.lubiduser.filters;

import com.lubid.lubiduser.Repository.JwtMappingRepository;
import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.config.auth.PrincipalDetail;
import com.lubid.lubiduser.model.JwtMappingUser;
import com.lubid.lubiduser.model.User;
import com.lubid.lubiduser.module.MakeJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Order(0)
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final MakeJWT makeJWT;

    private final UserRepository userRepository;

    private final JwtMappingRepository jwtMappingRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request);
        Claims tokenClaims = makeJWT.validateTokenAndGetSubject(token); // 토큰 검증, 에러 발생시 403

        User findUser = userRepository.findByUserName(tokenClaims.getSubject());

        // DB의 토큰 검증, 에러 발생시 자연스럽게 삭제
        Claims dbFindtokenClaims = makeJWT.validateTokenAndGetSubject(jwtMappingRepository.findByUser(findUser).getToken());

        if(dbFindtokenClaims.equals(tokenClaims)){
            // 토큰 검증과 동시에 아이디 값과 ROLE값을 넣어준다.
            PrincipalDetail user = new PrincipalDetail(User.builder().userName(tokenClaims.getSubject()).roles(tokenClaims.get("user_role").toString()).build());
            AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
            authenticated.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }else{
            JwtMappingUser findJwtMappingUser = jwtMappingRepository.findByUser(findUser);
            jwtMappingRepository.delete(findJwtMappingUser);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {"/auth/user/login", "/auth/user/join"};
        String path = request.getRequestURI();

        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }

    private String parseBearerToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }
}
