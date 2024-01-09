package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.DeliveryAddressRepository;
import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.common.module.AuthCheckModule;
import com.lubid.lubiduser.model.DeliveryAddress;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class DeliveryService {

    private final UserRepository userRepository;

    private final DeliveryAddressRepository addressRepository;

    private final AuthCheckModule authCheckModule;

    /**
     * 유저는 자기 자신의 주소 정보만 가져와야하기 때문에 자기만 확인하는 검증 코드가 있어야한다.
     * */
    @Transactional
    public ResponseEntity<String> insertDeliveryAddress(DeliveryAddress deliveryAddress){
        // db data
        User finduser =  userRepository.findByUserName(deliveryAddress.getUser().getUserName());

        if(finduser == null){
            return new ResponseEntity<>("sorry not found user", HttpStatus.BAD_REQUEST);
        }

        if(!authCheckModule.usernameCheckAuth(deliveryAddress.getUser().getUserName())){
            return new ResponseEntity<>("sorry you don't have auth", HttpStatus.FORBIDDEN);
        }

        deliveryAddress.setUser(finduser);
        addressRepository.save(deliveryAddress);
        return new ResponseEntity<>("success delivery date", HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity findAllDeliveryAddressofUser(String username){
        // db data
        User finduser =  userRepository.findByUserName(username);

        if(finduser == null){
            return new ResponseEntity<>("sorry not found user", HttpStatus.BAD_REQUEST);
        }

        if(authCheckModule.usernameCheckAuth(username)){
            return new ResponseEntity<>("sorry you don't have auth", HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity(addressRepository.findAllByUser(userRepository.findByUserName(username)),HttpStatus.OK);
    }

}
