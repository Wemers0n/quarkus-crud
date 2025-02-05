package com.example.services;

import java.util.List;
import java.util.UUID;

import com.example.exception.UserNotFoundException;
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
        return (User) User.findByIdOptional(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

    public List<User> findAll(Integer page, Integer pageSize){
        return User.findAll().page(page, pageSize).list();
    }

    @Transactional
    public void deleteUser(UUID id){
        var deleted = User.deleteById(id);
        if(!deleted){
            throw new UserNotFoundException("Failed to delete, user does not exist.");
        }
    }
}
