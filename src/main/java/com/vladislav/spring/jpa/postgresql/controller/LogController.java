package com.vladislav.spring.jpa.postgresql.controller;

import com.vladislav.spring.jpa.postgresql.dto.LogDto;
import com.vladislav.spring.jpa.postgresql.model.Log;
import com.vladislav.spring.jpa.postgresql.service.LogService;
import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {

	private final LogService logService;

	public LogController(LogService logService) {
		this.logService = logService;
	}

	@GetMapping("/logs")
	public ResponseEntity<List<Log>> getAllLogs(@RequestParam(required = false) String author) {
		return logService.getAllLogs(author);
	}

	@GetMapping("/logs/{id}")
	public ResponseEntity<Log> getLogById(@PathVariable("id") long id) {
		return logService.getLogById(id);
	}

	@PostMapping("/logs")
	public ResponseEntity<Log> createLog(@RequestBody LogDto logDto) {
		return logService.createLog(logDto);
	}

	@PutMapping("/logs/{id}")
	public ResponseEntity<Log> updateLog(@PathVariable("id") long id, @RequestBody LogDto logDto) {
		return logService.updateLog(id, logDto);
	}

	@DeleteMapping("/logs/{id}")
	public ResponseEntity<HttpStatus> deleteLog(@PathVariable("id") long id) {
		return logService.deleteLog(id);
	}

	@DeleteMapping("/logs")
	public ResponseEntity<HttpStatus> deleteAllLogs() {
		return logService.deleteAllLogs();
	}

}
