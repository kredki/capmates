package com.capgemini.jstk.capmates.repository.dao.interfaces;

import java.util.List;

import com.capgemini.jstk.capmates.repository.entities.PlayerBoardGameEntity;

public interface PlayerGamesDAO {

	List<Long> getPlayerGames(long playerId);

	PlayerBoardGameEntity addBoardGame(PlayerBoardGameEntity boardGameToAdd);

}