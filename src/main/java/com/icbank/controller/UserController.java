package com.icbank.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icbank.model.User;
import com.icbank.model.UserDTO;
import com.icbank.security.SecurityService;
import com.icbank.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user-managment")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@CrossOrigin
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

	@RequestMapping(value = "/transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> transfer(@RequestParam Long id, @RequestParam BigDecimal amount,
			@RequestParam String iban) {
		try {
			return new ResponseEntity<Boolean>(userService.transfer(id, amount, iban), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong with the server!",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public ResponseEntity<?> getInfo(@RequestParam Long id) {
		try {
			return new ResponseEntity<User>(userService.findByID(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Something went wrong with the server! " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
