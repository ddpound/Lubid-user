package com.lubid.lubiduser.dto;

import com.lubid.lubiduser.model.DeliveryAddress;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressDto {

    private UserDto userDto;
    private String address;
    private String detailAddress;
    private Timestamp createDate;
    private Timestamp updateDate;

    public DeliveryAddressDto(DeliveryAddress deliveryAddress){
        this.userDto = new UserDto(deliveryAddress.getUser());
        this.address = deliveryAddress.getAddress();
        this.detailAddress = deliveryAddress.getDetailAddress();
        this.createDate = deliveryAddress.getCreateDate();
        this.updateDate = deliveryAddress.getUpdateDate();
    }


}
