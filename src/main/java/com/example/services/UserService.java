package com.example.services;

import java.util.List;
import java.util.UUID;

import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public User save(User user){
        repository.persist(user);
        return user;
    }

    public User findUser(UUID id){
        return (User) repository.findByIdOptional(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

    public List<User> findAll(Integer page, Integer pageSize){
        return repository.findAll().page(page, pageSize).list();
    }

    @Transactional
    public void deleteUser(UUID id){
        var deleted = repository.deleteById(id);
        if(!deleted){
            throw new UserNotFoundException("Failed to delete, user does not exist.");
        }
    }
}
