package com.icbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icbank.model.User;
import com.icbank.model.UserDTO;
import com.icbank.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user-managment")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			return new ResponseEntity<User>(userService.register(user), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error occured while trying to register! " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<User>(userService.login(user.getUsername(), user.getPassword()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Wrong username or Password! " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
