package com.capgemini.jstk.capmates.repository.entities;

public class BoardGameEntity {
	private long id;
	private String title;
	private int playerQtyFrom;
	private int playerQtyTo;

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

	public int getPlayerQtFrom() {
		return playerQtyFrom;
	}

	public void setPlayerQtFrom(int playerQtFrom) {
		this.playerQtyFrom = playerQtFrom;
	}

	public int getPlayerQtTo() {
		return playerQtyTo;
	}

	public void setPlayerQtTo(int playerQtTo) {
		this.playerQtyTo = playerQtTo;
	}

}
