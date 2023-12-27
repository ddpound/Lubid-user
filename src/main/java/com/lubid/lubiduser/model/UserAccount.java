package com.lubid.lubiduser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    private String accountHolder;

    private String bank;

    private String accountNumber;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;
}
