package com.lubid.lubiduser.Service;

import com.lubid.lubiduser.Repository.UserRepository;
import com.lubid.lubiduser.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findUser(int userId){
        Optional<User> resultUser = userRepository.findById(userId);
        return resultUser.get();
    }

    public int createUser(User user){
        userRepository.save(user);
        return 0;
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

}
