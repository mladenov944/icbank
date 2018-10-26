package com.icbank.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icbank.model.User;

@Service
public class UserService extends BaseService implements UserDetailsService {

	public User register(User user) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		User tempUser = new User();
		tempUser.setFirstName(user.getFirstName());
		tempUser.setSecondName(user.getSecondName());
		tempUser.setSurname(user.getSurname());
		tempUser.setIban(user.getIban());
		tempUser.setEmail(user.getEmail());
		tempUser.setCash(user.getCash());
		tempUser.setUsername(user.getUsername());
		tempUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		tempUser.setEnabled(true);
		tempUser.setRole("ROLE_USER");
		getEm().persist(tempUser);
		return tempUser;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getEm().createNamedQuery("user.findUserByUsername", User.class).setParameter("pUsername", username)
				.getSingleResult();
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found.");
		}

		return user;
	}

	public User findByID(long id) {
		return getEm().find(User.class, id);

	}
}
