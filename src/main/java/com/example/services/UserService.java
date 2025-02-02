package com.example.services;

import com.example.model.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Transactional
    public User save(User user){
        User.persist(user);
        return user;
    }
}
