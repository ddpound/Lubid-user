package com.lubid.lubiduser.controller;


import com.lubid.lubiduser.Service.BusinessRegistrationService;
import com.lubid.lubiduser.model.BusinessRegistrationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 사업자 등록이 되어있는지 확인 하기 위한 컨트롤러
 * https://www.data.go.kr/data/15081808/openapi.do
 *
 *
 *
 * */
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "lubid-business-registration/user")
public class BusinessRegistrationController {

    private final BusinessRegistrationService businessRegistrationService;

    @PostMapping(value = "validation-business")
    private ResponseEntity<?> validationBusiness(@RequestBody List<BusinessRegistrationModel> businessList){


        return ResponseEntity.ok(businessList);
    }



}
