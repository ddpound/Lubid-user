package com.lubid.lubiduser.config.auth;

import com.lubid.lubiduser.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetail implements UserDetails {
    private User user; // 컴포지션(상속대신 품고있는것 )


    // 이거 컴포지션으로 안담아주면 기본 계정을 던져준다
    public PrincipalDetail(User yUser){
        this.user = yUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoleList().forEach(r->{
            authorities.add(()->r);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    // 계정이 만료되지 않았는지 리턴한다(true 잠기지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true; // true 안잠겨있다 라는 뜻
    }
    // 계정이 만료되지 않았는지 리턴한다(true 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정활성화가 완료 되어있는지 아닌지 (true를 해야 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
