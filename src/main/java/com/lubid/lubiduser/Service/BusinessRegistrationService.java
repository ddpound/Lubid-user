package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.model.BusinessRegistrationModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@PropertySource("classpath:commonKey.yml")
@Log4j2
@RequiredArgsConstructor
@Service
public class BusinessRegistrationService {

    @Value("${encoding-key}")
    String encodingKey;

    @Value("${url}")
    String url;

    private ResponseEntity<?> validationBusiness(List<BusinessRegistrationModel> businessRegistrationModels){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List<BusinessRegistrationModel>> request = new HttpEntity<>(businessRegistrationModels);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
