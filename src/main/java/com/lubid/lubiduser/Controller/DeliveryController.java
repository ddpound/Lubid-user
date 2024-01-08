package com.lubid.lubiduser.Controller;

import com.lubid.lubiduser.Service.DeliveryService;
import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.dto.UserDto;
import com.lubid.lubiduser.model.DeliveryAddress;
import com.lubid.lubiduser.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RequiredArgsConstructor
@RequestMapping(value = "delivery")
@RestController
public class DeliveryController {

    private final UserService userService;

    private final DeliveryService deliveryService;

    @PostMapping("save")
    public ResponseEntity saveAddress(@RequestBody DeliveryAddress deliveryAddress){
        return deliveryService.insertDeliveryAddress(deliveryAddress);
    }

    @GetMapping(value = "find/{userName}")
    public ResponseEntity findByAllDeliveryAddressoOfUser(@PathVariable String userName){
        return new ResponseEntity<>(deliveryService.findAllDeliveryAddressofUser(userName), HttpStatus.OK);
    }

}
