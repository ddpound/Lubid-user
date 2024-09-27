package com.lubid.lubiduser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class WishList extends CommonColumn{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long wishListId;

    @ManyToOne
    public User user;

    public String productId;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;
}
