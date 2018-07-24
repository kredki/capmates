package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;

public interface PlayerGamesDAO {

	List<BoardGameEntity> getPlayerGames(long playerId);

	BoardGameEntity addBoardGame(Long playerId, BoardGameEntity game);

	BoardGameEntity removeBoardGame(Long playerId, BoardGameEntity game);

}