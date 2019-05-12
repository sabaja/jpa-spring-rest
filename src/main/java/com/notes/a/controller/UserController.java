package com.notes.a.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notes.a.annotations.TrackTime;
import com.notes.a.entity.User;
import com.notes.a.exception.ResourceNotFoundException;
import com.notes.a.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/user")
	@TrackTime
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	// The @RequestBody annotation is used to bind the request body with a method
	// parameter.
	@PostMapping(value = "/user")
	public User createUser(@Valid @RequestBody User user) {
		log.debug("saving.. {}", user);
		return this.userRepository.save(user);
	}
	
	@GetMapping(value = "/user/{id}")
	public User getUserById(@PathVariable(value = "id") Long id) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

	@PutMapping(value = "/user/{id}")
	public User updateUserById(@PathVariable(value = "id") Long id, @Valid @RequestBody User users) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		user.setUserName(users.getUserName());
		user.setNotes(users.getNotes());
		return userRepository.save(user);
	}
	
	
	@DeleteMapping(value = "/user/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable(value = "id") Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
}
