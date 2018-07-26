package com.capgemini.jstk.capmates.services.dto;

public class GameToAddDTO {
	private String title;
	private int playerQtyFrom;
	private int playerQtyTo;

	public GameToAddDTO() {
		super();
	}

	public GameToAddDTO(String title, int playerQtyFrom, int playerQtyTo) {
		super();
		this.title = title;
		this.playerQtyFrom = playerQtyFrom;
		this.playerQtyTo = playerQtyTo;
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
