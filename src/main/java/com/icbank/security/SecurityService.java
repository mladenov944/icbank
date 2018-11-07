package com.icbank.security;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.icbank.model.User;
import com.icbank.service.BaseService;

@Service("securityService")
public class SecurityService extends BaseService implements UserDetailsService {

	private final String IBAN_PREFIX = "ICB00BG2112";

	// @Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userDao;

	public void register(String firstName, String secondName, String email, String surname, String username,
			String password) {
		int randomNumber = (int) (Math.random() * 123456 + 1256);
		User u = new User();
		u.setUsername(username);
		u.setFirstName(firstName);
		u.setSecondName(secondName);
		u.setSurname(surname);
		u.setEmail(email);
		u.setCash(BigDecimal.valueOf(500.00));
		u.setIban(IBAN_PREFIX + randomNumber);
		u.setPassword(passwordEncoder.encode(password));
		u.setEnabled(true);
		u.setRole("ROLE_USER");
		u.setCreatedDate(LocalDate.now());
		getEm().persist(u);
	}

	public List<User> list() throws UsernameNotFoundException {
		return userDao.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getEm().createQuery("Select u from User u where u.username = :pUsername", User.class)
				.setParameter("pUsername", username).getSingleResult();
	}

	public void changePassword(String username, String password) {
		User u = getEm().createQuery("Select u from User u where u.username = :pUsername", User.class)
				.setParameter("pUsername", username).getSingleResult();
		u.setPassword(passwordEncoder.encode(password));
	}

}
