package com.example.user.service.services;

import com.example.user.service.entities.User;

import java.util.List;

public interface UserService {
    //user operations

    //create user
    User saveUser(User user);

    //get All Users
    List<User> getAllUser();

    //get Single User of given userId
    User getUser(String userId);

    //delete

    //update
}
