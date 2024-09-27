package com.lubid.lubiduser.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class DeliveryAddress extends CommonColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String address;

    private String detailAddress;

    public void setDeliveryAddress(String address, String detailAddress){
        this.address = address;
        this.detailAddress = detailAddress;
    }

}
