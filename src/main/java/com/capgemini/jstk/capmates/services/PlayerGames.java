package com.capgemini.jstk.capmates.services;

import java.util.List;
import java.util.Optional;

import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;

public interface PlayerGames {

	List<BoardGameDTO> getAllGames();

	Optional<BoardGameDTO> getGame(long gameId);

	List<BoardGameDTO> getPlayerGames(long playerId);

	Optional<BoardGameDTO> addGame(Long playerId, GameToAddDTO gameToAdd);

	Optional<BoardGameDTO> addGame(Long playerId, BoardGameDTO gameToAdd);

}