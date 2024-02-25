package com.vladislav.spring.jpa.postgresql.service;

import com.vladislav.spring.jpa.postgresql.model.Log;

import java.util.List;
import java.util.Optional;

public interface LogService {

    List<Log> findAll();

    List<Log> findByIsLink(boolean published);

    List<Log> findByAuthorContaining(String author);

    Optional<Log> findById(long id);

    Log save(Log user);

    void deleteById(long id);

    void deleteAll();
}
