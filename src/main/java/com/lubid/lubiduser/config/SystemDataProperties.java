package com.lubid.lubiduser.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
@Data
public class SystemDataProperties {
    @Value("${system-data.super-admin-name}")
    private String superAdminName;

    @Value("${system-data.super-admin-pwd}")
    private String superAdminPwd;
}