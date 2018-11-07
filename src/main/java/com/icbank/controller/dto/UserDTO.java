package com.icbank.controller.dto;

import java.math.BigDecimal;

import com.icbank.security.UserAuthorityEnum;

public class UserDTO extends BaseEntityDTO {

	private String firstName;
	private String secondName;
	private String surname;
	private String username;
	private Boolean enabled;
	private String email;
	private String iban;
	private BigDecimal cash;
	private UserAuthorityEnum authority;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

	public UserAuthorityEnum getAuthority() {
		return authority;
	}

	public void setAuthority(UserAuthorityEnum authority) {
		this.authority = authority;
	}

}
