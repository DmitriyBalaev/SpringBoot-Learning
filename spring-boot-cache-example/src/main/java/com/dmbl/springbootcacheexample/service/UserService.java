package com.dmbl.springbootcacheexample.service;

import com.dmbl.springbootcacheexample.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(Long id);

    User createUser (String name, String email);

    List<User> getAll();

    User createOrReturnCached(User user);

    User createAndRefreshCache(User user);

    void delete(Long id);

    void deleteAndEvict(Long id);
}