package com.icbank.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.icbank.security.UserAuthorityEnum;

@Entity
@NamedQuery(name = "user.findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :pUsername")
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	@Size(max = 20)
	@NotNull
	String username;

	@Column
	@NotNull
	private String password;

	@Column
	@NotNull
	private String firstName;

	@Column
	@NotNull
	private String secondName;

	@Column
	@NotNull
	private String surname;

	@Column
	@NotNull
	private String email;

	@Column
	@NotNull
	private String iban;

	@Column
	@NotNull
	private BigDecimal cash;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private UserAuthorityEnum authority = UserAuthorityEnum.ROLE_USER;

	@Column(nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean enabled;

	@Column
	@NotNull
	private String role;

	@Column
	@NotNull
	private LocalDate createdDate;

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public UserAuthorityEnum getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthorityEnum authority) {
		this.authority = authority;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(authority.toString()));
		return auths;
	}

}
