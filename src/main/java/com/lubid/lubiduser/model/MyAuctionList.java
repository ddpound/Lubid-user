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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class MyAuctionList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myAuctionListId;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

}
