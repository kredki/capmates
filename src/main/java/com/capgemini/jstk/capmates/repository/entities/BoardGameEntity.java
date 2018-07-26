package com.capgemini.jstk.capmates.repository.entities;

public class BoardGameEntity {
	private long id;
	private String title;
	private int playerQtyFrom;
	private int playerQtyTo;

	public BoardGameEntity() {
		super();
	}

	public BoardGameEntity(long id, String title, int playerQtFrom, int playerQtTo) {
		super();
		this.id = id;
		this.title = title;
		this.playerQtyFrom = playerQtFrom;
		this.playerQtyTo = playerQtTo;
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

	public int getPlayerQtyFrom() {
		return playerQtyFrom;
	}

	public void setPlayerQtyFrom(int playerQtFrom) {
		this.playerQtyFrom = playerQtFrom;
	}

	public int getPlayerQtyTo() {
		return playerQtyTo;
	}

	public void setPlayerQtyTo(int playerQtTo) {
		this.playerQtyTo = playerQtTo;
	}

}
