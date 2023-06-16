package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(String username, String password){
        User user = new User(username,password);

//        user.setUserName(username);
//        user.setPassword(password);

        userRepository.save(user);

        return user;


    }

    public void deleteUser(int userId){
        User user = userRepository.findById(userId).get();

        userRepository.delete(user);

    }

    public User updateUser(Integer id, String password){
        User user = userRepository.findById(id).get();

        user.setPassword(password);
        userRepository.save(user);

        return user;


    }
}
