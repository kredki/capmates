package com.capgemini.jstk.capmates.services.dto;

public class StatDTO {
	private long playerId;
	private long gameId;
	private long rank;
	private long level;
	private long wins;
	private long loss;

	public StatDTO(long playerId, long gameId, long rank, long level, long wins, long loss) {
		super();
		this.playerId = playerId;
		this.gameId = gameId;
		this.rank = rank;
		this.level = level;
		this.wins = wins;
		this.loss = loss;
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

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public long getLevel() {
		return level;
	}

	public void setLevel(long level) {
		this.level = level;
	}

	public long getWins() {
		return wins;
	}

	public void setWins(long wins) {
		this.wins = wins;
	}

	public long getLoss() {
		return loss;
	}

	public void setLoss(long loss) {
		this.loss = loss;
	}
}