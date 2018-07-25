package com.capgemini.jstk.capmates.repository.entities;

public class HistoryEntity {
	private long playerId;
	private long gameId;
	private long points;

	public HistoryEntity(long playerId, long gameId, long points) {
		super();
		this.playerId = playerId;
		this.gameId = gameId;
		this.points = points;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getPoints() {
		return points;
	}

	public void setPoints(long points) {
		this.points = points;
	}
}