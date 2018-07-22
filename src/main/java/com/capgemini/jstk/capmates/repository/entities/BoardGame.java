package com.capgemini.jstk.capmates.repository.entities;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

@Component
public class BoardGame {
	private long id;
	private String title;
	private static final AtomicLong counter = new AtomicLong(1);

	public BoardGame(String title) {
		super();
		this.id = counter.getAndIncrement();
		this.title = title;
	}

}
