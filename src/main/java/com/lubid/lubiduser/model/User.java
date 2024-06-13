package com.lubid.lubiduser.model;

import com.lubid.lubiduser.enumpack.AuthAndRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String userName;

    private String password;

    private String email;

    private AuthAndRoles oauth;

    private String roles;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    // 활성화, 비활성화, 마지막 로그인 상태가 길때 자동 비활성화
    private Boolean enabled;

    // 탈퇴
    private Boolean withdrawal;

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            // , 로 스플릿 해서 배열로 리턴해준다
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
