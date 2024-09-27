package com.lubid.lubiduser.Repository;

import com.lubid.lubiduser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserName(String username);

}
