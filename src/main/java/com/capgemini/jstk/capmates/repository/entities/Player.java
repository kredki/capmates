package com.capgemini.jstk.capmates.repository.entities;

import org.springframework.stereotype.Component;

@Component
public class Player {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String motto;

	public Player(Long id, String firstName, String lastName, String email, String password,
			String motto) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.motto = motto;
	}
}
