package com.vladislav.spring.jpa.postgresql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vladislav.spring.jpa.postgresql.dto.UserDto;
import com.vladislav.spring.jpa.postgresql.model.User;
import com.vladislav.spring.jpa.postgresql.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllusers(@RequestParam(required = false) String Author) {
		try {
			List<User> users = new ArrayList<User>();

			if (Author == null)
				userService.findAll().forEach(users::add);
			else
				userService.findByAuthorContaining(Author).forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getTutorialById(@PathVariable("id") long id) {
		Optional<User> tutorialData = userService.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/users")
	public ResponseEntity<User> createTutorial(@RequestBody UserDto tutorialDto) {
		try {
			User tutorial = new User(tutorialDto.getAuthor(), tutorialDto.getDescription(), tutorialDto.isLink());
			User _tutorial = userService.save(tutorial);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateTutorial(@PathVariable("id") long id, @RequestBody UserDto tutorialDto) {
		Optional<User> tutorialData = userService.findById(id);

		if (tutorialData.isPresent()) {
			User tutorial = tutorialData.get();
			tutorial.setAuthor(tutorialDto.getAuthor());
			tutorial.setDescription(tutorialDto.getDescription());
			tutorial.setLink(tutorialDto.isLink());
			return new ResponseEntity<>(userService.save(tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			userService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllusers() {
		try {
			userService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/users/links")
	public ResponseEntity<List<User>> findByLink() {
		try {
			List<User> user = userService.findByIsLink(true);

			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/searchByAuthor")
	public ResponseEntity<List<User>> getTutorialByAuthor(@RequestParam("Author") String Author) {
		try {
			List<User> user = userService.findByAuthorContaining(Author);

			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
