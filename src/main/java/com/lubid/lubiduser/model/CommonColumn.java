package com.lubid.lubiduser.model;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@MappedSuperclass
public class CommonColumn{

    @CreationTimestamp
    private Timestamp createDate;

    private Long createUser;

    @UpdateTimestamp
    private Timestamp updateDate;

    private Long updateUser;

    private Boolean isDeleted;

    private String remarks;

    // 첫 데이터를 만들때 사용될 세팅 메소드
    public void createData(Long createUserId) {
        this.isDeleted = false;
        this.createUser = createUserId;
    }

    // 객체 삭제처리
    public void deleteData(Long deleteUserId) {
        this.isDeleted = true;
        this.updateUser = deleteUserId;
    }

    public void updateData(Long updateUserId) {
        this.updateUser = updateUserId;
    }

    public void writeRemarks(String remarks) {
        this.remarks = remarks;
    }
}
