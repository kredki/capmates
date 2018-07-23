package com.capgemini.jstk.capmates.repository.entities;

import org.springframework.stereotype.Component;

@Component
public class BoardGame {
	private long id;
	private String title;

	public BoardGame(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

}
