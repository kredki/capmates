package com.capgemini.jstk.capmates.repository.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;

public interface BoardGameDAO {

	List<BoardGameEntity> getBoardGames();

	Optional<BoardGameEntity> getBoardGameById(long id);

	Optional<BoardGameEntity> getBoardGameByTitle(String title);

	BoardGameEntity updateBoardGame(BoardGameEntity boardGame);

	Optional<BoardGameEntity> addBoardGame(BoardGameEntity gameToAdd);

	List<BoardGameEntity> getBoardGamesById(List<Long> ids);

}