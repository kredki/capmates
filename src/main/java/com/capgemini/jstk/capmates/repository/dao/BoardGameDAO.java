package com.capgemini.jstk.capmates.repository.dao;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;

public interface BoardGameDAO {

	List<BoardGameEntity> getBoardGames();

	Optional<BoardGameEntity> getBoardGameById(long id);

	Optional<BoardGameEntity> getBoardGameByTitle(String title);

	boolean updateBoardGame(BoardGameEntity boardGame);

	boolean addBoardGame(GameToAddDTO gameToAdd);

}