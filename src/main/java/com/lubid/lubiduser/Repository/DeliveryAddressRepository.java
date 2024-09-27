package com.lubid.lubiduser.Repository;

import com.lubid.lubiduser.model.DeliveryAddress;
import com.lubid.lubiduser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    // User와 관련된 주소 전부 가져오기
    List<DeliveryAddress> findAllByUser(User user);

}
