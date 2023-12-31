package com.lubid.lubiduser.dto;

import com.lubid.lubiduser.enumpack.AuthAndRoles;
import com.lubid.lubiduser.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    private String userName;
    private String email;
    private AuthAndRoles oauth;
    private AuthAndRoles roles;
    private Timestamp createDate;
    private Timestamp updateDate;

    UserDto(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.oauth = user.getOauth();
        this.roles = user.getRoles();
        this.createDate = user.getCreateDate();
        this.updateDate = user.getUpdateDate();
    }
}
