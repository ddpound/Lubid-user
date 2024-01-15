package com.lubid.lubiduser.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String address;

    private String detailAddress;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    public void setDeliveryAddress(String address, String detailAddress){
        this.address = address;
        this.detailAddress = detailAddress;
    }

}
