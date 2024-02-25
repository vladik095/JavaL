package com.vladislav.spring.jpa.postgresql.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vladislav.spring.jpa.postgresql.dto.LogDto;
import com.vladislav.spring.jpa.postgresql.model.Log;
import com.vladislav.spring.jpa.postgresql.service.LogService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LogController {
	private final LogService userService;

	public LogController(LogService userService) {
		this.userService = userService;
	}

	@GetMapping("/logs")
	public ResponseEntity<List<Log>> getAllusers(@RequestParam(required = false) String author) {
		try {
			List<Log> users = new ArrayList<Log>();

			if (author == null)
				userService.findAll().forEach(users::add);
			else
				userService.findByAuthorContaining(author).forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/logs/{id}")
	public ResponseEntity<Log> getLogById(@PathVariable("id") long id) {
		Optional<Log> logData = userService.findById(id);

		if (logData.isPresent()) {
			return new ResponseEntity<>(logData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/logs")
	public ResponseEntity<Log> createLog(@RequestBody LogDto logDto) {
		try {
			Log log = new Log(logDto.getAuthor(), logDto.getDescription(), logDto.isLink());
			Log savedUser = userService.save(log);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Log(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/logs/{id}")
	public ResponseEntity<Log> updateLog(@PathVariable("id") long id, @RequestBody LogDto logDto) {
		Optional<Log> logData = userService.findById(id);

		if (logData.isPresent()) {
			Log log = logData.get();
			log.setAuthor(logDto.getAuthor());
			log.setDescription(logDto.getDescription());
			log.setLink(logDto.isLink());
			return new ResponseEntity<>(userService.save(log), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/logs/{id}")
	public ResponseEntity<HttpStatus> deleteLog(@PathVariable("id") long id) {
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/logs")
	public ResponseEntity<HttpStatus> deleteAllusers() {
		try {
			userService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/logs/links")
	public ResponseEntity<List<Log>> findByLink() {
		try {
			List<Log> user = userService.findByIsLink(true);

			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/log/searchByAuthor")
	public ResponseEntity<List<Log>> getLogByAuthor(@RequestParam("author") String author) {
		try {
			List<Log> user = userService.findByAuthorContaining(author);

			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
