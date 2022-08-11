package com.dmbl.springbootcacheexample.service;

import com.dmbl.springbootcacheexample.entity.User;
import com.dmbl.springbootcacheexample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#name")
    public User createUser(String name, String email) {
        log.info("creating user with parameters: {}, {}", name, email);
        return userRepository.save(new User(name, email));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#user.name")
    public User createOrReturnCached(User user) {
        log.info("creating user: {}", user);
        return userRepository.save(user);
    }

    @Override
    @CachePut(value = "users", key = "#user.name")
    public User createAndRefreshCache(User user) {
        log.info("creating user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        log.info("deleting user by id:{}", id);
        userRepository.deleteById(id);
    }

    @Override
    @CacheEvict("users")
    public void deleteAndEvict(Long id) {
        log.info("deleting user by id:{}", id);
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable("users")
    public User getUser(Long id) {
        log.info("getting user by id: {}", id);
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found by id " + id));
    }


}
