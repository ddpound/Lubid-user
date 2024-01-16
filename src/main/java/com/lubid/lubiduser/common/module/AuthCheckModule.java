package com.lubid.lubiduser.common.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@Component
public class AuthCheckModule {

    public boolean usernameCheckAuth(String checkUserName){
        // security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null){
            UserDetails userDetails = (UserDetails)principal;
            String sessionUsername = userDetails.getUsername();

            return sessionUsername.equals(checkUserName);
        }else{
            return false;
        }
    }

}
