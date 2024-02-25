package com.vladislav.spring.jpa.postgresql.service;

import com.vladislav.spring.jpa.postgresql.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    List<User> findByIsLink(boolean published);

    List<User> findByAuthorContaining(String author);

    Optional<User> findById(long id);

    User save(User user);

    void deleteById(long id);

    void deleteAll();
}
