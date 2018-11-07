package com.icbank.service;

import java.math.BigDecimal;
import java.time.LocalDate;

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
		tempUser.setCreatedDate(LocalDate.now());
		;
		getEm().persist(tempUser);
		return tempUser;

	}

	@CrossOrigin
	public User login(String username, String password) {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		User temp = (User) loadUserByUsername(username);
		if (bCryptPasswordEncoder.matches(password, temp.getPassword())) {
			return temp;
		}
		throw new UsernameNotFoundException("Wrong username or password! ");
	}

	public boolean transfer(Long id, BigDecimal amount, String iban) {

		User sender = (User) findByID(id);
		User receiver = (User) findByIban(iban);
		if (receiver == null) {
			throw new UsernameNotFoundException("Wrong IBAN !");
		}

		if (sender.getCash().compareTo(amount) >= 0) {
			sender.setCash(sender.getCash().subtract(amount));
			receiver.setCash(receiver.getCash().add(amount));
			return true;
		}
		throw new UsernameNotFoundException("Not enough cash!");
	}

	@Override
	@CrossOrigin
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = getEm().createNamedQuery("user.findUserByUsername", User.class).setParameter("pUsername", username)
				.getSingleResult();
		if (user == null) {
			throw new UsernameNotFoundException("Username " + username + " not found.");
		}

//		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//				user.isEnabled(), true, true, true, authorities);

		return user;
	}

	public User findByID(Long id) {
		return getEm().createQuery("Select u from User u where u.id = :pId", User.class).setParameter("pId", id)
				.getSingleResult();
	}

	public User findByIban(String iban) {
		return getEm().createQuery("Select u from User u where u.iban = :pIban", User.class).setParameter("pIban", iban)
				.getSingleResult();
	}

}
