package com.capgemini.jstk.capmates.repository.entities;

public class PlayerBoardGameEntity {
	long playerId;
	long boardGameId;

	public PlayerBoardGameEntity() {
		super();
	}

	public PlayerBoardGameEntity(long playerId, long boardGameId) {
		super();
		this.playerId = playerId;
		this.boardGameId = boardGameId;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public long getBoardGameId() {
		return boardGameId;
	}

	public void setBoardGameId(long boardGameId) {
		this.boardGameId = boardGameId;
	}

}
