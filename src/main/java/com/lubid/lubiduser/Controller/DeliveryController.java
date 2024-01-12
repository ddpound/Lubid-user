package com.lubid.lubiduser.Controller;

import com.lubid.lubiduser.Service.DeliveryService;
import com.lubid.lubiduser.Service.UserService;
import com.lubid.lubiduser.model.DeliveryAddress;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        return new ResponseEntity<>(deliveryService.findAllDeliveryAddressOfUser(userName), HttpStatus.OK);
    }

}
