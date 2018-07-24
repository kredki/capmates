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
public class PlayerGamesList implements PlayerGamesDAO {
	private final List<PlayerBoardGameEntity> playerBoardGamesList;
	private final PlayerDAO players;
	private final BoardGameDAO boardGames;

	@Autowired
	public PlayerGamesList(PlayerDAO players, BoardGameDAO boardGames) {
		super();
		this.playerBoardGamesList = new ArrayList<>();
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

	@Override
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

	@Override
	public BoardGameEntity addBoardGame(Long playerId, BoardGameEntity game) {
		// todo
		return null;
	}

	@Override
	public BoardGameEntity removeBoardGame(Long playerId, BoardGameEntity game) {
		// todo
		return null;
	}
}
