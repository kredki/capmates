package com.capgemini.jstk.capmates.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.jstk.capmates.repository.dao.BoardGameDAO;
import com.capgemini.jstk.capmates.repository.dao.PlayerGamesDAO;
import com.capgemini.jstk.capmates.repository.entities.BoardGameEntity;
import com.capgemini.jstk.capmates.services.dto.BoardGameDTO;

@Service
public class PlayerGamesService {
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

	public List<BoardGameDTO> getAllGames() {
		List<BoardGameEntity> games = this.boardGameDAO.getBoardGames();
		return this.mapper.map(games, BoardGameDTO.class);
	}

	public List<BoardGameDTO> getPlayerGames(long playerId) {
		List<Long> gameIds = this.playerGamesDAO.getPlayerGames(playerId);
		List<BoardGameEntity> games = this.boardGameDAO.getBoardGamesById(gameIds);
		return mapper.map(games, BoardGameDTO.class);
	}
}