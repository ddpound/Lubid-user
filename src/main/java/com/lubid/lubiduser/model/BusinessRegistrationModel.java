package com.lubid.lubiduser.model;


import lombok.Data;

import java.util.List;

/**
 * 사업자 등록 정보 API 요청을 위한 객체
 *
 * */
@Data
public class BusinessRegistrationModel {
    private String b_no;
    private String start_dt;
    private String p_nm;
    private String p_nm2;
    private String b_nm;
    private String corp_no;
    private String b_sector;
    private String b_type;
    private String b_adr;
}
