package com.example.services;

import java.util.List;
import java.util.UUID;

import com.example.exception.UserNotFoundException;
import com.example.model.User;
import com.example.repository.UserRepository;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @CacheInvalidate(cacheName = "users_cache")
    public User save(User user){
        repository.persist(user);
        return user;
    }

    public User findUser(UUID id){
        return (User) repository.findByIdOptional(id).orElseThrow(() -> new UserNotFoundException("User does not exist"));
    }

    @CacheResult(cacheName = "users_cache")
    public List<User> findAll(Integer page, Integer pageSize){
        return repository.findAll().page(page, pageSize).list();
    }

    @Transactional
    @CacheInvalidate(cacheName = "users_cache")
    public void deleteUser(UUID id){
        var deleted = repository.deleteById(id);
        if(!deleted){
            throw new UserNotFoundException("Failed to delete, user does not exist.");
        }
    }
}
