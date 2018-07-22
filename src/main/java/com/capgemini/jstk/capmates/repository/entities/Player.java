package com.capgemini.jstk.capmates.repository.entities;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class Player {
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String motto;
	private static final AtomicLong counter = new AtomicLong(1);

	public Player(String firstName, String lastName, String email, String password, String motto) {
		super();
		this.id = counter.getAndIncrement();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.motto = motto;
	}
}
