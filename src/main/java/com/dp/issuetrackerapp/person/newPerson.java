package com.dp.issuetrackerapp.person;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dp.issuetrackerapp.validation.FieldMatch;
import com.dp.issuetrackerapp.validation.ValidEmail;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "matchingPassword", message = "Heslá sa musia zhodovať")})
public class newPerson {

	@NotNull(message = "je povinný údaj")
	@Size(min = 1, message = "je povinný údaj")
	private String password;

	@NotNull(message = "je povinný údaj")
	@Size(min = 1, message = "je povinný údaj")
	private String matchingPassword;

	@NotNull(message = "je povinný údaj")
	@Size(min = 1, message = "je povinný údaj")
	private String firstName;

	@NotNull(message = "je povinný údaj")
	@Size(min = 1, message = "je povinný údaj")
	private String lastName;

	@ValidEmail
	@NotNull(message = "je povinný údaj")
	@Size(min = 1, message = "je povinný údaj")
	private String email;

	public newPerson() {

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
