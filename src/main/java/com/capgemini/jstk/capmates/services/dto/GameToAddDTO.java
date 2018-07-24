package com.capgemini.jstk.capmates.services.dto;

public class GameToAddDTO {
	private String title;
	private int playerQtyFrom;
	private int playerQtyTo;

	public GameToAddDTO(String title, int playerQtFrom, int playerQtTo) {
		super();
		this.title = title;
		this.playerQtyFrom = playerQtFrom;
		this.playerQtyTo = playerQtTo;
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
