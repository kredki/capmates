package com.capgemini.jstk.capmates.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.repository.dao.BoardGameDAO;
import com.capgemini.jstk.capmates.repository.dao.PlayerGamesDAO;
import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;
import com.capgemini.jstk.capmates.repository.entities.PlayerBoardGameEntity;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;
import com.capgemini.jstk.capmates.services.dto.GameToAddDTO;

@Service
public class PlayerGamesService implements PlayerGames {
	BoardGameDAO boardGameDAO;
	PlayerGamesDAO playerGamesDAO;
	ModelMapper mapper;

	@Autowired
	public PlayerGamesService(BoardGameDAO boardGameDAO, PlayerGamesDAO playerGamesDAO) {
		super();
		this.boardGameDAO = boardGameDAO;
		this.playerGamesDAO = playerGamesDAO;
		this.mapper = new ModelMapper();
	}

	@Override
	public List<BoardGameDTO> getAllGames(String title, int playerQtyFrom, int playerQtyTo) {
		List<BoardGameEntity> returnedGames = this.boardGameDAO.getBoardGames();
		List<BoardGameEntity> games = new ArrayList<>();
		games = returnedGames.stream()
				.filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase())
						&& x.getPlayerQtyFrom() >= playerQtyFrom && x.getPlayerQtyTo() <= playerQtyTo)
				.collect(Collectors.toList());
		java.lang.reflect.Type targetListType = new TypeToken<List<BoardGameDTO>>() {
		}.getType();
		return this.mapper.map(games, targetListType);
	}

	@Override
	public List<BoardGameDTO> getPlayerGames(long playerId) {
		List<Long> gameIds = this.playerGamesDAO.getPlayerGames(playerId);
		List<BoardGameEntity> games = this.boardGameDAO.getBoardGamesById(gameIds);
		java.lang.reflect.Type targetListType = new TypeToken<List<BoardGameDTO>>() {
		}.getType();
		return mapper.map(games, targetListType);
	}

	@Override
	public Optional<BoardGameDTO> addGame(Long playerId, GameToAddDTO gameToAdd) {
		Optional<BoardGameEntity> game = this.boardGameDAO.getBoardGameByTitle(gameToAdd.getTitle());
		if (game.isPresent()) {
			this.playerGamesDAO.addBoardGame(new PlayerBoardGameEntity(playerId, game.get().getId()));
			return Optional.ofNullable(mapper.map(game.get(), BoardGameDTO.class));
		} else {
			Optional<BoardGameEntity> addedGame = this.boardGameDAO
					.addBoardGame(mapper.map(gameToAdd, BoardGameEntity.class));
			if (addedGame.isPresent()) {
				return Optional.ofNullable(mapper.map(addedGame.get(), BoardGameDTO.class));
			} else {
				return Optional.ofNullable(null);
			}
		}
	}

	@Override
	public Optional<BoardGameDTO> addGame(Long playerId, BoardGameDTO gameToAdd) {
		Optional<BoardGameEntity> game = this.boardGameDAO.getBoardGameById(gameToAdd.getId());
		if (game.isPresent()) {
			this.playerGamesDAO.addBoardGame(new PlayerBoardGameEntity(playerId, game.get().getId()));
			return Optional.ofNullable(mapper.map(game, BoardGameDTO.class));
		} else {
			Optional<BoardGameDTO> addedGame = addGame(playerId, mapper.map(gameToAdd, GameToAddDTO.class));
			if (addedGame.isPresent()) {
				return Optional.ofNullable(addedGame.get());
			}
		}
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<BoardGameDTO> getGame(long gameId) {
		Optional<BoardGameEntity> game = this.boardGameDAO.getBoardGameById(gameId);
		if (game.isPresent()) {
			return Optional.ofNullable(mapper.map(game.get(), BoardGameDTO.class));
		}
		return Optional.ofNullable(null);
	}
}