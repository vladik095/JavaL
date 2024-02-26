package com.vladislav.spring.jpa.postgresql.service;

import com.vladislav.spring.jpa.postgresql.dto.LogDto;
import com.vladislav.spring.jpa.postgresql.model.Log;
import com.vladislav.spring.jpa.postgresql.repository.LogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LogService {

    private final LogRepository userRepository;

    public LogService(LogRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Log> findAll() {
        return userRepository.findAll();
    }

    public List<Log> findByIsLink(boolean published) {
        return userRepository.findByIsLink(published);
    }

    public List<Log> findByAuthorContaining(String author) {
        return userRepository.findByAuthorContaining(author);
    }

    public Optional<Log> findById(long id) {
        return userRepository.findById(id);
    }

    public Log save(Log user) {
        return userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public ResponseEntity<List<Log>> getAllLogs(String author) {
        try {
            List<Log> logs = new ArrayList<>();
            if (author == null)
                userRepository.findAll().forEach(logs::add);
            else
                userRepository.findByAuthorContaining(author).forEach(logs::add);

            if (logs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Log> getLogById(long id) {
        Optional<Log> logData = userRepository.findById(id);

        if (logData.isPresent()) {
            return new ResponseEntity<>(logData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Log> createLog(LogDto logDto) {
        try {
            Log log = new Log(logDto.getAuthor(), logDto.getDescription(), logDto.isLink());
            Log savedLog = userRepository.save(log);
            return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Log> updateLog(long id, LogDto logDto) {
        Optional<Log> logData = userRepository.findById(id);

        if (logData.isPresent()) {
            Log log = logData.get();
            log.setAuthor(logDto.getAuthor());
            log.setDescription(logDto.getDescription());
            log.setLink(logDto.isLink());
            return new ResponseEntity<>(userRepository.save(log), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteLog(long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllLogs() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
