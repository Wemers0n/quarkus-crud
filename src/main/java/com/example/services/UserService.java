package com.example.services;

import java.util.List;
import java.util.UUID;

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

    public User findUser(UUID id){
        return User.findById(id);
    }

    public List<User> findAll(Integer page, Integer pageSize){
        return User.findAll().page(page, pageSize).list();
    }
}
