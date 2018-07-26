package com.capgemini.jstk.capmates.services.dto;

public class PlayerToAddDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String motto;

	public PlayerToAddDTO() {
		super();
	}

	public PlayerToAddDTO(String firstName, String lastName, String email, String password, String motto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.motto = motto;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}
}
