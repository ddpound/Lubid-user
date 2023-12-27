package com.lubid.lubiduser.Repository;

import com.lubid.lubiduser.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepositiry extends JpaRepository<UserAccount, Integer> {
}
