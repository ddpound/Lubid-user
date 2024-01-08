package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.DeliveryAddressRepository;
import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.DeliveryAddress;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final UserRepository userRepository;

    private final DeliveryAddressRepository addressRepository;

    @Transactional
    public ResponseEntity<String> insertDeliveryAddress(DeliveryAddress deliveryAddress){
        User finduser =  userRepository.findByUserName(deliveryAddress.getUser().getUserName());
        deliveryAddress.setUser(finduser);
        addressRepository.save(deliveryAddress);
        return new ResponseEntity<>("success delivery date", HttpStatus.OK);
    }

    public List<DeliveryAddress> findAllDeliveryAddressofUser(String username){
        return addressRepository.findAllByUser(userRepository.findByUserName(username));
    }

}
