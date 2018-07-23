package com.capgemini.jstk.capmates.repository.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;
import com.capgemini.jstk.capmates.repository.entities.PlayerBoardGameEntity;
import com.capgemini.jstk.capmates.repository.entities.PlayerEntity;

@Repository
@Scope("singleton")
public class PlayersGamesList {
	private final List<PlayerBoardGameEntity> playerBoardGamesList;
	private final List<BoardGameEntity> gameList;
	private final PlayerList players;
	private final BoardGameList boardGames;

	@Autowired
	public PlayersGamesList(BoardGameList gameList, PlayerList players, BoardGameList boardGames) {
		super();
		this.playerBoardGamesList = new ArrayList<>();
		this.gameList = gameList.getBoardGames();
		this.players = players;
		this.boardGames = boardGames;
	}

	@PostConstruct
	private void init() {
		List<BoardGameEntity> boardGameEntities = boardGames.getBoardGames();
		for (PlayerEntity player : players.getPlayers()) {
			this.playerBoardGamesList.add(new PlayerBoardGameEntity(player.getId(), boardGameEntities.get(1).getId()));
			this.playerBoardGamesList.add(new PlayerBoardGameEntity(player.getId(), boardGameEntities.get(2).getId()));
		}
		this.playerBoardGamesList.add(new PlayerBoardGameEntity(1L, 4L));
		this.playerBoardGamesList.add(new PlayerBoardGameEntity(2L, 4L));
		this.playerBoardGamesList.add(new PlayerBoardGameEntity(3L, 3L));
	}

	public List<BoardGameEntity> getPlayerGames(long playerId) {
		List<BoardGameEntity> result = new ArrayList<>();
		for (PlayerBoardGameEntity playerBoardGameEntity : this.playerBoardGamesList) {
			long matchPlayerId = playerBoardGameEntity.getPlayerId();
			long boardGameId = playerBoardGameEntity.getBoardGameId();
			if (matchPlayerId == playerId) {
				result.add(this.boardGames.getBoardGameById(boardGameId).get());
			}
		}
		return result;
	}
}
