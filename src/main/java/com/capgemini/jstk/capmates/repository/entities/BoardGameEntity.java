package com.capgemini.jstk.capmates.repository.entities;

public class BoardGameEntity {
	private long id;
	private String title;

	public BoardGameEntity(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
