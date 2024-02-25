package com.vladislav.spring.jpa.postgresql.service;

import com.vladislav.spring.jpa.postgresql.model.Log;
import com.vladislav.spring.jpa.postgresql.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository userRepository;

    @Autowired
    public LogServiceImpl(LogRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Log> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Log> findByIsLink(boolean published) {
        return userRepository.findByIsLink(published);
    }

    @Override
    public List<Log> findByAuthorContaining(String author) {
        return userRepository.findByAuthorContaining(author);
    }

    @Override
    public Optional<Log> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Log save(Log user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
