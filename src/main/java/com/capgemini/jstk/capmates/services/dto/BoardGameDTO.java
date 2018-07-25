package com.capgemini.jstk.capmates.services.dto;

public class BoardGameDTO {
	private long id;
	private String title;
	private int playerQtyFrom;
	private int playerQtyTo;

	public BoardGameDTO(long id, String title, int playerQtyFrom, int playerQtyTo) {
		super();
		this.id = id;
		this.title = title;
		this.playerQtyFrom = playerQtyFrom;
		this.playerQtyTo = playerQtyTo;
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

	public void setPlayerQtyFrom(int playerQtyFrom) {
		this.playerQtyFrom = playerQtyFrom;
	}

	public int getPlayerQtyTo() {
		return playerQtyTo;
	}

	public void setPlayerQtyTo(int playerQtyTo) {
		this.playerQtyTo = playerQtyTo;
	}
}