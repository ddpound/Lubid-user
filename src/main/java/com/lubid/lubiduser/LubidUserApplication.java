package com.lubid.lubiduser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LubidUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LubidUserApplication.class, args);
    }

}
