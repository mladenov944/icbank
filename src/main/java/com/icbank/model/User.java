package com.icbank.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User extends BaseEntity {

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

}
