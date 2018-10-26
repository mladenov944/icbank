package com.icbank.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icbank.model.User;

@Service
public class UserService extends BaseService {

	public User register(User user) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		User tempUser = new User();
		tempUser.setUsername(user.getUsername());
		tempUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		tempUser.setEnabled(true);
		tempUser.setRole("ROLE_USER");
		getEm().persist(tempUser);
		return tempUser;

	}
}
