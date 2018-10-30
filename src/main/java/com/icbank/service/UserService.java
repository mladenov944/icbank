package com.icbank.service;

import java.math.BigDecimal;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.icbank.model.User;

@Service
@CrossOrigin
public class UserService extends BaseService implements UserDetailsService {

	@CrossOrigin
	public User register(User user) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

		String ibanche = "BG00ICB";
		int randIban = (int) (Math.random() * 10000 + 137527491);
		String finalIban = ibanche + randIban;

		User tempUser = new User();
		tempUser.setFirstName(user.getFirstName());
		tempUser.setSecondName(user.getSecondName());
		tempUser.setSurname(user.getSurname());
		tempUser.setIban(finalIban);
		tempUser.setEmail(user.getEmail());
		tempUser.setCash(BigDecimal.valueOf(500));
		tempUser.setUsername(user.getUsername());
		tempUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		tempUser.setEnabled(true);
		tempUser.setRole("ROLE_USER");
		getEm().persist(tempUser);
		return tempUser;

	}

	@CrossOrigin
	public User login(String username, String password) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String kriptnata = bCryptPasswordEncoder.encode(password);
		User temp = (User) loadUserByUsername(username);
		if (bCryptPasswordEncoder.matches(password, kriptnata)) {
			return temp;
		}
		throw new UsernameNotFoundException("Wrong username or password! ");
	}

	@Override
	@CrossOrigin
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
