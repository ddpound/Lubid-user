package com.lubid.lubiduser.config.auth;

import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername 작동확인, login username : " + username);
        User principal = userRepository.findByUserName(username);

        if(principal == null) return (UserDetails) new UsernameNotFoundException("해당 사용자를 찾을수 없습니다 : " + username);

        log.info("Now Login User : "+ principal.getUserName());
        return new PrincipalDetail(principal); // 시큐리티 세션에 유저정보가 저장
    }
}
