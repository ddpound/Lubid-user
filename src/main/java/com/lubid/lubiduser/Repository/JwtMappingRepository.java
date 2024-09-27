package com.lubid.lubiduser.Repository;

import com.lubid.lubiduser.model.JwtMappingUser;
import com.lubid.lubiduser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtMappingRepository extends JpaRepository<JwtMappingUser, Long> {

    JwtMappingUser findByUser(User user);

}
