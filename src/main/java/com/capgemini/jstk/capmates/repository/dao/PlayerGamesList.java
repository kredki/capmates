package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.PlayerBoardGameEntity;

@Repository
@Scope("singleton")
public class PlayerGamesList implements PlayerGamesDAO {
	private final List<PlayerBoardGameEntity> playerBoardGamesList;

	public PlayerGamesList() {
		super();
		this.playerBoardGamesList = new ArrayList<>();
	}

	@PostConstruct
	private void init() {
		for (int i = 1; i < 6; i++) {
			this.playerBoardGamesList.add(new PlayerBoardGameEntity(i, 2L));
			this.playerBoardGamesList.add(new PlayerBoardGameEntity(i, 3L));
		}

		this.playerBoardGamesList.add(new PlayerBoardGameEntity(1L, 4L));
		this.playerBoardGamesList.add(new PlayerBoardGameEntity(2L, 4L));
		this.playerBoardGamesList.add(new PlayerBoardGameEntity(3L, 3L));
	}

	@Override
	public List<Long> getPlayerGames(long playerId) {
		List<Long> gameIds = this.playerBoardGamesList.stream().filter(x -> x.getPlayerId() == playerId)
				.map(PlayerBoardGameEntity::getBoardGameId).collect(Collectors.toList());
		return gameIds;
	}

	@Override
	public PlayerBoardGameEntity addBoardGame(PlayerBoardGameEntity boardGameToAdd) {
		long playerId = boardGameToAdd.getPlayerId();
		long gameId = boardGameToAdd.getBoardGameId();
		for (PlayerBoardGameEntity game : this.playerBoardGamesList) {
			if (game.getPlayerId() == playerId && game.getBoardGameId() == gameId) {
				return game;
			}
		}
		this.playerBoardGamesList.add(boardGameToAdd);
		return boardGameToAdd;
	}
}
